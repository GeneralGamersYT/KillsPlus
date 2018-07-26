package ninja.oscaz.killsplus.gui.commandgui;

import me.rayzr522.jsonmessage.JSONMessage;
import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.gui.GuiManager;
import ninja.oscaz.killsplus.gui.MenuListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CommandMenuManager implements MenuListener {

    private final Main main;
    private final GuiManager guiManager;

    private final CommandMenu commandMenu = new CommandMenu();

    public CommandMenuManager(Main main, GuiManager guiManager) {
        this.main = main;
        this.guiManager = guiManager;
    }

    public CommandMenu getCommandMenu() {
        return commandMenu;
    }

    @Override
    public void manageEvent(InventoryClickEvent event) {
        if (event.getSlot() == 4) {
            this.main.getManager().set("commands.enabled", ! (boolean) this.main.getManager().get("commands.enabled"));
            event.getWhoClicked().openInventory(guiManager.getCMenuManager().getCommandMenu().getGui(main));
            return;
        }
        if (event.getSlot() == 22) {
            sendCommands((Player) event.getWhoClicked());
        }
    }

    public void sendCommands(Player player) {
        for (int i = 0; i < 250; i++) {
            player.sendMessage("");
        }
        for (int i = 0; i < main.getConfig().getStringList("onkill.commands").size(); i++) {
            String command = main.getConfig().getStringList("onkill.commands").get(i);
            JSONMessage.create("[+]")
                    .color(ChatColor.GREEN)
                    .tooltip("Add")
                    .runCommand("/killsplus command add")
                    .then(" [-]")
                    .color(ChatColor.RED)
                    .tooltip("Remove")
                    .runCommand("/killsplus command remove " + command)
                    .then(" " + command)
                    .color(ChatColor.AQUA)
                    .send(player);
        }
    }
}
