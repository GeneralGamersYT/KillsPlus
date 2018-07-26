package ninja.oscaz.killsplus.listener;

import ninja.oscaz.killsplus.DatabaseManager;
import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.gui.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final DatabaseManager manager;
    private final Main main;

    public InventoryClickListener(Main main) {
        this.main = main;
        this.manager = main.getManager();
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getClickedInventory().getName().equalsIgnoreCase(Title.INDEX.getTitle())) {
            main.getGuiManager().getMenuManager().manageEvent(event);
            return;
        }
        if (event.getClickedInventory().getName().equalsIgnoreCase(Title.HEAD.getTitle())) {
            main.getGuiManager().getHMenuManager().manageEvent(event);
            return;
        }
    }
}
