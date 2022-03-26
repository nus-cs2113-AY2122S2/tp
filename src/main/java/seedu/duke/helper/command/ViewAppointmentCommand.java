package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class ViewAppointmentCommand extends Command {
    public ViewAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List appointmentList) throws NotFoundException {
        if (parameterArray == null) {
            appointmentList.view();
        } else {
            String parameters = String.join(",",parameterArray);
            appointmentList.view(parameters);
        }
        return Status.FIND_APPOINTMENT_SUCCESS;
    }
}
