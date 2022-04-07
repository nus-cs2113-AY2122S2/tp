package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class CheckIfAppointmentTodayCommand extends Command {

    public CheckIfAppointmentTodayCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List appointmentList) throws DuplicateEntryException, NotFoundException, HalpmiException {
        if (appointmentList instanceof AppointmentList) {
            ((AppointmentList) appointmentList).hasAppointmentToday("P",parameterArray[0]);
        }
        return Status.APPOINTMENT_FOUND_SUCCESS;
    }
}
