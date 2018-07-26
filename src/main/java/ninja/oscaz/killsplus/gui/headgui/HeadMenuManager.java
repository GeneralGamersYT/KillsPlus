package ninja.oscaz.killsplus.gui.headgui;

import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.Items;
import ninja.oscaz.killsplus.gui.GuiManager;
import ninja.oscaz.killsplus.gui.MenuListener;
import ninja.oscaz.killsplus.gui.Offset;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.math.BigDecimal;

public class HeadMenuManager implements MenuListener {

    private final Main main;
    private final GuiManager guiManager;

    private final HeadMenu headMenu = new HeadMenu();

    public HeadMenuManager(Main main, GuiManager guiManager) {
        this.main = main;
        this.guiManager = guiManager;
    }

    @Override
    public void manageEvent(InventoryClickEvent event) {

        if (event.getSlot() == Items.ARROW_INT) {
            event.setCancelled(true);
            event.getWhoClicked().openInventory(main.getGuiManager().getMenuManager().getMenu().getGui((Player) event.getWhoClicked(), main));
            return;
        }

        if (event.getSlot() == 13) {
            boolean headEnabled = main.getManager().get("heads.enabled");
            main.getManager().set("heads.enabled", !headEnabled);
            event.setCancelled(true);
            return;
        }

        double dChance = main.getManager().get("heads.chance");
        BigDecimal chance = BigDecimal.valueOf(dChance);

        for (Offset offset : Offset.values()) {
            if (offset.getSlot() == event.getSlot()) {
                if (chance.add(offset.getAmount()).doubleValue() > 100.0 || chance.add(offset.getAmount()).doubleValue() < 0.0) {
                    event.setCancelled(true);
                    return;
                } else {
                    chance = chance.add(offset.getAmount());
                }
            }
        }

        event.setCancelled(true);

        main.getManager().set("heads.chance", chance.doubleValue());

        event.getWhoClicked().openInventory(headMenu.getGui(this.main, (Player) event.getWhoClicked()));
    }

    public HeadMenu getHeadMenu() {
        return headMenu;
    }
}