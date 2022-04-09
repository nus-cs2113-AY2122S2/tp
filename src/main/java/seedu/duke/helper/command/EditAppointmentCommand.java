package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class EditAppointmentCommand extends Command {

    public EditAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List appointmentList) throws NotFoundException {
        appointmentList.edit(parameterArray);
        return Status.EDIT_APPOINTMENT_SUCCESS;
    }
}