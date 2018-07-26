package ninja.oscaz.killsplus.gui.headgui;

import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.Items;
import ninja.oscaz.killsplus.gui.Offset;
import ninja.oscaz.killsplus.gui.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;

public class HeadMenu {

    public Inventory getGui(Main main, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, Title.HEAD.getTitle());

        IntStream.range(0, 27).forEach(index -> inventory.setItem(index, Items.getPane()));

        Arrays.stream(Offset.values()).forEach(offset ->
                inventory.setItem(offset.getSlot(), getIncrementWool(offset.getAmount().doubleValue()))
        );

        inventory.setItem(13, getHead(player, main));

        inventory.setItem(Items.ARROW_INT, Items.getBackArrow());

        return inventory;
    }

    private ItemStack getHead(Player player, Main main) {

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setDisplayName(ChatColor.GRAY + "Enable / Disable");
        skullMeta.setOwner(player.getName());

        boolean enabled = main.getManager().get("heads.enabled");

        double dChance = main.getManager().get("heads.chance");
        BigDecimal chance = BigDecimal.valueOf(dChance);

        skullMeta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY + "Enabled: " +
                (enabled ? ChatColor.GREEN + "True" : ChatColor.RED + "False"),
                ChatColor.DARK_GRAY + "Current Chance: " + ChatColor.YELLOW +
                (enabled ? chance + "%" : "0%")
        ));

        head.setItemMeta(skullMeta);

        return head;
    }

    private ItemStack getIncrementWool(double increment) {
        ItemStack wool = new ItemStack(Material.WOOL);
        wool.setDurability(increment < 0 ? (short) 14 : (short) 5);
        ItemMeta woolMeta = wool.getItemMeta();
        if (increment < 0) woolMeta.setDisplayName("Decrease by " + (increment * -1) + "%");
        else woolMeta.setDisplayName("Increase by " + increment + "%");
        wool.setItemMeta(woolMeta);
        return wool;
    }


}
