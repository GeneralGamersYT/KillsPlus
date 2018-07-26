package ninja.oscaz.killsplus.gui.taggui;

import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.gui.GuiManager;
import ninja.oscaz.killsplus.gui.MenuListener;
import ninja.oscaz.killsplus.gui.Offset;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.math.BigDecimal;
import java.util.Arrays;

public class TagMenuManager implements MenuListener {

    private final Main main;
    private final GuiManager guiManager;

    private final TagMenu tagMenu = new TagMenu();

    public TagMenuManager(Main main, GuiManager guiManager) {
        this.main = main;
        this.guiManager = guiManager;
    }

    public TagMenu getTagMenu() {
        return tagMenu;
    }

    @Override
    public void manageEvent(InventoryClickEvent event) {
        if (event.getSlot() == 0) {
            event.setCancelled(true);
            event.getWhoClicked().openInventory(guiManager.getMenuManager().getMenu().getGui((Player) event.getWhoClicked(), main));
            return;
        }
        Arrays.stream(Option.values())
                .filter(option -> option.getSlot() == event.getSlot())
                .findFirst()
                .ifPresent(option -> {
                    main.getTagInput().put(event.getWhoClicked().getUniqueId(), option.getKey());
                    event.getWhoClicked().sendMessage(ChatColor.GREEN + "You are setting the message for " + option.getMessage() + " remember you can also use colour codes!");
                });
        Arrays.stream(Offset.values())
                .forEach(offset -> {
                    BigDecimal time = BigDecimal.valueOf(main.getManager().get("tag.time"));
                    time = time.add(offset.getAmount());
                    main.getManager().set("tag.time", time.doubleValue());
                });
    }
}
