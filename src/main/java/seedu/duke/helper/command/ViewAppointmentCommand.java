package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.HalpmiException;
import seedu.duke.status.Status;

public class ViewAppointmentCommand extends Command {
    public ViewAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List appointmentList) throws HalpmiException {
        if (parameterArray == null) {
            appointmentList.view();
        } else {
            throw new HalpmiException("View Appointment Command only accepts null parameters!");
        }
        return Status.VIEW_SUCCESS;
    }
}
