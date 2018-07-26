package ninja.oscaz.killsplus.gui.taggui;

import org.bukkit.ChatColor;

enum Option {

    TAG_START(19, ChatColor.RED + "Set tag message", "tag message", (short) 5, "tag.tagged"),
    TAG_COMMAND(23, ChatColor.GREEN + "Set tag command message", "/ct command message, remember to use %s for seconds left", (short) 5, "tag.command"),
    TAG_END(21, ChatColor.GREEN + "Set no longer tagged message", "no longer tagged message", (short) 14, "tag.notag"),
    TAG_LOG(25, ChatColor.RED + "Set combat log message", "message broadcast on combat log", (short) 14, "tag.log");

    Option(int slot, String itemName, String message, short durability, String key) {
        this.slot = slot;
        this.itemName = itemName;
        this.message = message;
        this.durability = durability;
        this.key = key;
    }

    private final int slot;
    private final String itemName;
    private final String message;
    private final short durability;
    private final String key;

    public int getSlot() {
        return this.slot;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getMessage() { return this.message; }

    public short getDurability() {
        return this.durability;
    }

    public String getKey() {
        return key;
    }
}
