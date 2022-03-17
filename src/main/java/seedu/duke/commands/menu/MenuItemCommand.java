package seedu.duke.commands.menu;

import seedu.duke.commands.Command;
import seedu.duke.ui.MenuUi;

public abstract class MenuItemCommand extends Command {
    //any error messages here...
    public static final String COMMAND_WORD = "1";
    /**
     * Original string.
     */
    static final String ORIGINAL = "original ";

    /**
     * Desired string.
     */
    static final String DESIRED = "desired ";

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
