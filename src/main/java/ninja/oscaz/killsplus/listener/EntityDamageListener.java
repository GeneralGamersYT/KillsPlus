package ninja.oscaz.killsplus.listener;

import ninja.oscaz.killsplus.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    private final Main main;

    public EntityDamageListener(Main main) {
        this.main = main;
    }

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void on(EntityDamageByEntityEvent event) {
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
        Player entity = (Player) event.getEntity();
        Player damager = (Player) entity.getLastDamageCause().getEntity();
        if (main.getManager().get("tag.enabled")) {
            // add tag message on tag
            main.addTaggedPlayer(entity.getUniqueId());
            main.addTaggedPlayer(damager.getUniqueId());
        }
    }

}
