package ninja.oscaz.killsplus.listener;

import ninja.oscaz.killsplus.DatabaseManager;
import ninja.oscaz.killsplus.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatListener implements Listener {

    private final Main main;
    private final DatabaseManager manager;

    public ChatListener(Main main) {
        this.main = main;
        this.manager = main.getManager();
    }

    @EventHandler
    public void on(AsyncPlayerChatEvent event) {
        if (main.getTagInput().get(event.getPlayer().getUniqueId()) != null) {
            main.getManager().set(main.getTagInput().get(event.getPlayer().getUniqueId()), event.getMessage());
            main.getTagInput().remove(event.getPlayer().getUniqueId());
            event.setCancelled(true);
        }
        if (main.getCommandInput().contains(event.getPlayer().getUniqueId())) {
            List<String> commands = main.getConfig().getStringList("commands.onkill");
            commands.add(event.getMessage());
            main.getConfig().set("commands.onkill", commands);
            main.getCommandInput().remove(event.getPlayer().getUniqueId());
            event.setCancelled(true);
        }
    }

}
