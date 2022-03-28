package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class DeleteAppointmentCommand extends Command {

    public DeleteAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List appointmentList) throws NotFoundException {
        appointmentList.remove(parameterArray[0]);
        return Status.DELETE_APPOINTMENT_SUCCESS;
    }
}
