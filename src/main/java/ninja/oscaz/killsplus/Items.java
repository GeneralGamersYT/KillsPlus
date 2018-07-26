package ninja.oscaz.killsplus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

    private Items() {
        throw new IllegalStateException("Cannot be instantiated!");
    }

    // Global
    public static final int ARROW_INT = 0;

    public static ItemStack getPane() {
        ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE);
        pane.setDurability((short) 7);
        ItemMeta paneMeta = pane.getItemMeta();
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);
        return pane;
    }

    public static ItemStack getBackArrow() {
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName(ChatColor.RED + "Back");
        arrow.setItemMeta(arrowMeta);
        return arrow;
    }

}
