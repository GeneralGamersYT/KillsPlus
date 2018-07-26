package ninja.oscaz.killsplus.gui;

import org.bukkit.ChatColor;

public enum Title {

    /*
    public static final String MENU_NAME = ChatColor.RED + "KillsPlus - Main menu";
    public static final String MENU_HEAD_NAME = ChatColor.RED + "KillsPlus - Head menu";
    public static final String MENU_TAG_NAME = ChatColor.RED + "KillsPlus - Combat tag menu";
    public static final String MENU_COMMAND_NAME = ChatColor.RED + "KillsPlus - Command menu";
     */

    INDEX(ChatColor.RED + "KillsPlus - Main menu"),
    HEAD(ChatColor.RED + "KillsPlus - Head menu"),
    TAG(ChatColor.RED + "KillsPlus - Combat tag menu"),
    COMMAND(ChatColor.RED + "KillsPlus - Command menu");

    Title(String title) {
        this.title = title;
    }

    private final String title;

    public String getTitle() {
        return title;
    }

}
