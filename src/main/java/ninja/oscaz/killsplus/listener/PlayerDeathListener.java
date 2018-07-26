package ninja.oscaz.killsplus.listener;

import ninja.oscaz.killsplus.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerDeathListener implements Listener {

    private final Main main;

    public PlayerDeathListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void on(PlayerDeathEvent event) {
        if (this.main.getManager().get("heads.enabled")) {
            if (ThreadLocalRandom.current().nextDouble(100) <= (double) this.main.getManager().get("heads.chance")) {
                this.dropHead(event.getEntity(), event.getEntity().getLocation());
            }
        }
        if (this.main.getManager().get("commands.enabled")) {
            for (String command : main.getConfig().getStringList("commands.onkill")) {
                if (!command.contains("%killer%")) {
                    main.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", event.getEntity().getName()));
                    continue;
                } else {
                    if (event.getEntity().getKiller() == null) {
                        continue;
                    }
                    main.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", event.getEntity().getName()).replace("%killer%", event.getEntity().getKiller().getName()));
                }
            }
        }
    }

    private void dropHead(Player player, Location location) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwningPlayer(player);
        head.setItemMeta(skullMeta);
        skullMeta.setDisplayName(ChatColor.RED + "Head Configuration");

        player.getWorld().dropItemNaturally(location, head);
    }

}
