package ninja.oscaz.killsplus;

import ninja.oscaz.killsplus.command.CombatTagCommand;
import ninja.oscaz.killsplus.command.KillsplusCommand;
import ninja.oscaz.killsplus.gui.GuiManager;
import ninja.oscaz.killsplus.listener.ChatListener;
import ninja.oscaz.killsplus.listener.EntityDamageListener;
import ninja.oscaz.killsplus.listener.InventoryClickListener;
import ninja.oscaz.killsplus.listener.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    private final DatabaseManager manager = new DatabaseManager(this);

    private final List<String> disabledWorlds = new ArrayList<>();

    private final GuiManager guiManager = new GuiManager(this);

    private final Map<UUID, String> tagInput = new HashMap<>();
    private final Set<UUID> commandInput = new HashSet<>();

    private final Map<UUID, Double> tagged = new HashMap<>();

    private final Map<Runnable, Integer> runnableMap = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (manager.get("version") == null || (double) manager.get("version") != 3.0) {
            manager.set("pvp.enableall", "set");
            manager.set("heads.enabled", true);
            manager.set("heads.chance", 50.00);
            manager.set("tag.enabled", true);
            manager.set("tag.time", 15.00);
            manager.set("commands.enabled", true);
            manager.set("version", 3.0);
        }

        this.getCommand("killsplus").setExecutor(new KillsplusCommand(this));
        this.getCommand("comattag").setExecutor(new CombatTagCommand(this));
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        this.getConfig().getStringList("pvp.disabledworlds").forEach(disabledWorlds::add);
    }

    @Override
    public void onDisable() {
        this.getConfig().set("pvp.disabledworlds", disabledWorlds);
        this.disabledWorlds.clear();
    }

    public DatabaseManager getManager() {
        return manager;
    }

    public List<String> getDisabledWorlds() {
        return disabledWorlds;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }

    public Map<UUID, String> getTagInput() {
        return tagInput;
    }

    public Set<UUID> getCommandInput() {
        return commandInput;
    }

    public Map<UUID, Double> getTagged() { return tagged; }

    public Map<Runnable, Integer> getRunnableMap() {
        return runnableMap;
    }

    public void addTaggedPlayer(UUID uuid) {
        if (this.tagged.get(uuid) != null) {
            this.tagged.put(uuid, this.manager.get("tag.time"));
        } else {
            this.tagged.put(uuid, this.manager.get("tag.time"));
            this.startTaggedTimer(uuid);
        }
    }

    private void startTaggedTimer(UUID uuid) {
        Runnable runnable = () -> {
            Double time = Main.this.getTagged().get(uuid);
            if (time <= 0) {
                Main.this.getTagged().remove(uuid);
                Bukkit.getScheduler().cancelTask(Main.this.getRunnableMap().get(this));
            } else {
                // find player that has uuid and send no longer tagged message
                Main.this.getTagged().put(uuid, time - 1);
            }
        };
        Integer id = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, runnable, 1L, 0L);
        runnableMap.put(runnable, id);
    }

}
