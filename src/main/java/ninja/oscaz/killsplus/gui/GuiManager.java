package ninja.oscaz.killsplus.gui;

import ninja.oscaz.killsplus.Main;
import ninja.oscaz.killsplus.gui.commandgui.CommandMenuManager;
import ninja.oscaz.killsplus.gui.headgui.HeadMenuManager;
import ninja.oscaz.killsplus.gui.menugui.MenuManager;
import ninja.oscaz.killsplus.gui.taggui.TagMenuManager;

public class GuiManager {

    private final Main main;

    private final CommandMenuManager cMenuManager;
    private final HeadMenuManager hMenuManager;
    private final MenuManager menuManager;
    private final TagMenuManager tagMenuManager;

    public GuiManager(Main main) {
        this.main = main;
        this.cMenuManager = new CommandMenuManager(main, this);
        this.hMenuManager = new HeadMenuManager(main, this);
        this.menuManager = new MenuManager(main, this);
        this.tagMenuManager = new TagMenuManager(main, this);
    }

    public CommandMenuManager getCMenuManager() {
        return cMenuManager;
    }

    public HeadMenuManager getHMenuManager() {
        return hMenuManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public TagMenuManager getTagMenuManager() {
        return tagMenuManager;
    }

    public Main getMain() {
        return main;
    }

}
