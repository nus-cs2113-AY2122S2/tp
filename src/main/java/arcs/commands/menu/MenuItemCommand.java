package arcs.commands.menu;

import arcs.commands.Command;
import arcs.ui.MenuUi;

public abstract class MenuItemCommand extends Command {
    //any error messages here...
    public static final String COMMAND_WORD = "1";

    /**
     * Ui object.
     */
    final MenuUi ui;

    /**
     * Initializes Ui object for subclasses.
     *
     * @param ui Ui object.
     */
    MenuItemCommand(MenuUi ui) {
        this.ui = ui;
    }
}
