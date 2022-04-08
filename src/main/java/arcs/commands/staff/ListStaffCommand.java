package arcs.commands.staff;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.staff.Staff;

import java.util.ArrayList;

public class ListStaffCommand extends Command {
    public static final String COMMAND_WORD = "listStaffs";
    private static final String FEEDBACK = "Existing staffs information: ";

    @Override
    public CommandResult execute() {
        ArrayList<Staff> staffs = staffManager.getAllStaffs();
        ArrayList<String> staffsInfo = new ArrayList<>();
        for (Staff staff: staffs) {
            staffsInfo.add(staff.getStaffInfo());
        }
        return new CommandResult(FEEDBACK, staffsInfo);
    }
}

