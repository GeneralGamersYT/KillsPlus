package ninja.oscaz.killsplus.gui;

import org.bukkit.ChatColor;

public enum Title {

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
