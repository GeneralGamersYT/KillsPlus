package ninja.oscaz.killsplus.gui.menugui;

import ninja.oscaz.killsplus.DatabaseManager;
import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.Items;
import ninja.oscaz.killsplus.gui.GuiManager;
import ninja.oscaz.killsplus.gui.MenuListener;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuManager implements MenuListener {

    private final Main main;
    private final DatabaseManager manager;
    private final GuiManager guiManager;

    private final Menu menu = new Menu();

    public MenuManager(Main main, GuiManager guiManager) {
        this.main = main;
        this.manager = main.getManager();
        this.guiManager = guiManager;
    }

    @Override
    public void manageEvent(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getSlot() == 10) {
            event.getWhoClicked().openInventory(guiManager.getHMenuManager().getHeadMenu().getGui(main, (Player) event.getWhoClicked()));
            return;
        }
        if (event.getSlot() == 12) {
            event.getWhoClicked().openInventory(guiManager.getTagMenuManager().getTagMenu().getGui(main));
            return;
        }
        if (event.getSlot() == 14) {
            event.getWhoClicked().openInventory(guiManager.getCMenuManager().getCommandMenu().getGui(main));
            return;
        }
        if (event.getSlot() == Items.ARROW_INT) {
            event.getWhoClicked().closeInventory();
            return;
        }
        if (event.getSlot() == 16) {
            if (event.getClick() == ClickType.LEFT) {
                if (manager.get("pvp.enableall").equals("false")) {
                    manager.set("pvp.enableall", "true");
                } else if (manager.get("pvp.enableall").equals("true")) {
                    manager.set("pvp.enableall", "false");
                } else if (manager.get("pvp.enableall").equals("set")) {
                    manager.set("pvp.enableall", "true");
                }
                event.getWhoClicked().openInventory(menu.getGui((Player) event.getWhoClicked(), this.main));
            }
            if (event.getClick() == ClickType.RIGHT) {
                if (!(manager.get("pvp.enableall").equals("set"))) {
                    manager.set("pvp.enableall", "set");
                }
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

}
