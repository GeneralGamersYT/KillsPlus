package ninja.oscaz.killsplus.gui.menugui;

import ninja.oscaz.killsplus.DatabaseManager;
import ninja.oscaz.killsplus.Items;
import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.gui.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Menu {

    public Inventory getGui(Player player, Main main) {
        Inventory inventory = Bukkit.createInventory(null, 27, Title.INDEX.getTitle());

        IntStream.range(0, 27).forEach(index -> inventory.setItem(index, Items.getPane()));

        inventory.setItem(Items.ARROW_INT, getArrow());
        inventory.setItem(10, getSword(main.getManager()));
        inventory.setItem(12, getHead(player));
        inventory.setItem(14, getTag());
        inventory.setItem(16, getCommandBlock());

        return inventory;
    }

    private ItemStack getHead(Player player) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwner(player.getName());
        head.setItemMeta(skullMeta);
        skullMeta.setDisplayName(ChatColor.RED + "Head Configuration");

        return head;
    }

    private ItemStack getSword(DatabaseManager manager) {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(
                ChatColor.GREEN + "Enable" +
                        ChatColor.DARK_GRAY + " / " +
                        ChatColor.RED + "Disable" +
                        ChatColor.GRAY + " PVP"
        );
        swordMeta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY + "Mode: " + pvpEnableDesc(manager),
                ChatColor.GRAY + "Left click to enable/disable.",
                ChatColor.GRAY + "Right click to set specific worlds."
        ));
        sword.setItemMeta(swordMeta);
        return sword;
    }

    private String pvpEnableDesc(DatabaseManager manager) {
        if (manager.get("pvp.enableall").equals("true")) {
            return ChatColor.GREEN + "Enabled";
        }
        if (manager.get("pvp.enableall").equals("false")) {
            return ChatColor.RED + "Disabled";
        }
        if (manager.get("pvp.enableall").equals("set")) {
            return ChatColor.GRAY + "Set";
        }
        throw new IllegalStateException("pvp.enableall has no legal value!");
    }

    private ItemStack getTag() {
        ItemStack tag = new ItemStack(Material.NAME_TAG);
        ItemMeta tagMeta = tag.getItemMeta();
        tagMeta.setDisplayName(ChatColor.RED + "Combat Tag Configuration");
        tag.setItemMeta(tagMeta);
        return tag;
    }

    private ItemStack getCommandBlock() {
        ItemStack commandBlock = new ItemStack(Material.COMMAND);
        ItemMeta commandMeta = commandBlock.getItemMeta();
        commandMeta.setDisplayName(ChatColor.RED + "Run Command Configuration");
        commandBlock.setItemMeta(commandMeta);

        return commandBlock;
    }

    private ItemStack getArrow() {
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName(ChatColor.RED + "Close");
        arrow.setItemMeta(arrowMeta);
        return arrow;
    }

}
