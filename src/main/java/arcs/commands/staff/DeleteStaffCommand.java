package arcs.commands.staff;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.staff.Staff;
import arcs.data.exception.ArcsException;

public class DeleteStaffCommand extends Command  {
    public static final String COMMAND_WORD = "deleteStaff";
    private int index;
    private static final String SUCCESS_MESSAGE = "OK! The following staff has been deleted:";

    public DeleteStaffCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            Staff deleted = staffManager.deleteStaff(index);
            result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                    + deleted.getStaffInfo());
        } catch (ArcsException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
