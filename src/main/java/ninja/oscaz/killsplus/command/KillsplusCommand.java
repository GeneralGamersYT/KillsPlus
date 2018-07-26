package ninja.oscaz.killsplus.command;

import co.insou.colorchar.ColorChar;
import co.insou.commands.CommandConsumer;
import ninja.oscaz.killsplus.DatabaseManager;
import ninja.oscaz.killsplus.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class KillsplusCommand extends CommandConsumer {

    private final Main main;
    private final DatabaseManager manager;

    public KillsplusCommand(Main main) {
        super("killsplus", true);
        this.main = main;
        this.manager = main.getManager();
    }

    @Override
    public void onCommand(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            Arrays.asList(
                    ColorChar.color("&bKillsplus: &eExecutes this commandgui."),
                    ColorChar.color("&bKillsplus menugui: &eOpens the configuration panel."),
                    ColorChar.color("&bKillsplus disable: &eDisables all features."),
                    ColorChar.color("&bKillsplus enable: &eEnables all features.")
            ).forEach(player::sendMessage);
        }

        if (args.length > 0 && (args[0].equalsIgnoreCase("menu")
                || args[0].equalsIgnoreCase("gui")
                || args[0].equalsIgnoreCase("config"))
                || args[0].equalsIgnoreCase("cfg")) {
            player.openInventory(main.getGuiManager().getMenuManager().getMenu().getGui(player, main));
        }

        if (args.length > 2 && args[0].equalsIgnoreCase("command") && args[1].equalsIgnoreCase("add")) {
            player.sendMessage(ChatColor.AQUA + "You are adding a command to trigger on death.");
            player.sendMessage(ChatColor.AQUA + "You may use the placeholders %player% and %killer%");
            player.sendMessage(ChatColor.AQUA + "If the command does not contain %killer% it will trigger");
            player.sendMessage(ChatColor.AQUA + "on an environmental death, otherwise, pvp only.");
            main.getCommandInput().add(player.getUniqueId());
        }

        if (args.length > 2 && args[0].equalsIgnoreCase("command") && args[1].equalsIgnoreCase("remove")) {
            String command = "";
            for (int i = 2; i < args.length; i++) {
                command += args[i] + " ";
            }
            command = command.substring(0, command.length() - 1);
            List<String> commands = main.getConfig().getStringList("onkill.commands");
            commands.remove(command);
            main.getConfig().set("onkill.commands", commands);
            main.getGuiManager().getCMenuManager().sendCommands(player);
        }
    }
}
