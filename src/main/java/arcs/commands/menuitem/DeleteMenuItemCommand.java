package arcs.commands.menuitem;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItem;
import arcs.data.exception.ArcsException;

public class DeleteMenuItemCommand extends Command {

    public static final String COMMAND_WORD = "deleteMenuItem";
    private int index;
    private static final String SUCCESS_MESSAGE = "OK! The following menu item has been deleted:";

    public DeleteMenuItemCommand(int index) {
        this.index = index;
    }

    /**
     * Executes Command to delete menu item and returns the result of the command executed.
     *
     * @return CommandResult of the executed command.
     */
    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            MenuItem deleted = menuItemManager.deleteMenuItem(index);
            result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                    + deleted.getMenuItemInfo());
        } catch (ArcsException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
