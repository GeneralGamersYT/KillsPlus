package ninja.oscaz.killsplus;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DatabaseManager
{
    private final Main plugin;
    private final FileConfiguration database;
    private final File file;

    public DatabaseManager(Main plugin)
    {
        this.plugin = plugin;

        this.file = new File(plugin.getDataFolder(), "database.yml");
        if (!this.file.exists()) {
            try
            {
                this.file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        this.database = YamlConfiguration.loadConfiguration(this.file);
    }

    public void set(String key, Object value)
    {
        this.database.set(key, value);
        saveAsync();
    }

    public <T> T get(String key)
    {
        return (T)this.database.get(key);
    }

    public FileConfiguration getDatabase()
    {
        return this.database;
    }

    public boolean contains(String key)
    {
        return this.database.contains(key);
    }

    private void saveAsync()
    {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, DatabaseManager.this::save);
    }

    private synchronized void save()
    {
        synchronized (this)
        {
            try
            {
                this.database.save(this.file);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

