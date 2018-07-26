package ninja.oscaz.killsplus.listener;

import ninja.oscaz.killsplus.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    private final Main main;

    public EntityDamageListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
        // Pvp - enable / disabled / set
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        if (main.getManager().get("pvp.enableall").equals("false")) {
            event.setCancelled(true);
            return;
        }
        if (main.getDisabledWorlds().contains(event.getEntity().getWorld().getName())) {
            event.setCancelled(true);
            return;
        }

    }

}
