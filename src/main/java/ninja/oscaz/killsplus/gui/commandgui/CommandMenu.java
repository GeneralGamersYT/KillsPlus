package ninja.oscaz.killsplus.gui.commandgui;

import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.gui.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CommandMenu {

    public Inventory getGui(Main main) {
        Inventory inventory = Bukkit.createInventory(null, 27, Title.COMMAND.getTitle());

        inventory.setItem(4, getCommand(main));
        inventory.setItem(22, getViewItem());

        return inventory;
    }

    private ItemStack getCommand(Main main) {
        ItemStack item = new ItemStack(Material.COMMAND);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "Enable / Disable");
        boolean enabled = main.getManager().get("commands.enabled");
        itemMeta.setLore(Collections.singletonList(
                ChatColor.GRAY + "Enabled: " + (enabled ? ChatColor.GREEN + "True" : ChatColor.RED + "False")
        ));
        item.setItemMeta(itemMeta);
        return item;
    }

    private ItemStack getViewItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "View / Add / Remove commands");
        item.setItemMeta(itemMeta);
        return item;
    }

}
