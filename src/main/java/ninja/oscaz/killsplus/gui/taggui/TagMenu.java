package ninja.oscaz.killsplus.gui.taggui;

import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.Items;
import ninja.oscaz.killsplus.gui.Offset;
import ninja.oscaz.killsplus.gui.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;

public class TagMenu {

    public Inventory getGui(Main main) {
        Inventory inventory = Bukkit.createInventory(null, 27, Title.TAG.getTitle());

        IntStream.range(0, 27).forEach(index -> inventory.setItem(index, Items.getPane()));

        Arrays.stream(Option.values()).forEach(option -> inventory.setItem(option.getSlot(), getSetWool(option)));
        Arrays.stream(Offset.values()).forEach(offset -> inventory.setItem(offset.getSlot(), getIncrementWool(offset.getAmount().doubleValue())));

        inventory.setItem(13, getTag(main));

        return inventory;
    }

    private ItemStack getSetWool(Option option) {
        ItemStack wool = new ItemStack(Material.WOOL);
        wool.setDurability(option.getDurability());
        ItemMeta woolMeta = wool.getItemMeta();
        woolMeta.setDisplayName(option.getItemName());
        wool.setItemMeta(woolMeta);
        return wool;
    }

    private ItemStack getTag(Main main) {
        ItemStack tag = new ItemStack(Material.NAME_TAG);
        ItemMeta tagMeta = tag.getItemMeta();
        double dChance = main.getManager().get("tag.time");
        BigDecimal chance = BigDecimal.valueOf(dChance);
        tagMeta.setDisplayName(ChatColor.GRAY + "Current Time: " + ChatColor.YELLOW + chance + "s");
        tag.setItemMeta(tagMeta);
        return tag;
    }

    private ItemStack getIncrementWool(double increment) {
        ItemStack wool = new ItemStack(Material.WOOL);
        wool.setDurability(increment < 0 ? (short) 14 : (short) 5);
        ItemMeta woolMeta = wool.getItemMeta();
        if (increment < 0) woolMeta.setDisplayName("Decrease by " + (increment * -1));
        else woolMeta.setDisplayName("Increase by " + increment);
        wool.setItemMeta(woolMeta);
        return wool;
    }

}
