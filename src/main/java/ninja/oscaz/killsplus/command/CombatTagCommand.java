package ninja.oscaz.killsplus.command;

import co.insou.colorchar.ColorChar;
import co.insou.commands.CommandConsumer;
import ninja.oscaz.killsplus.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatTagCommand extends CommandConsumer {

    private final Main main;

    public CombatTagCommand(Main main) {
        super("combattime", true);
        this.main = main;
    }

    @Override
    public void onCommand(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;
        Double time = main.getTagged().get(player.getUniqueId());
        if (time == null) {
            player.sendMessage(ChatColor.RED + "Error! You are not in combat.");
            return;
        }
        player.sendMessage(ColorChar.color(main.getManager().get("tag.command").toString().replace("%s", time.toString())));
    }
}
