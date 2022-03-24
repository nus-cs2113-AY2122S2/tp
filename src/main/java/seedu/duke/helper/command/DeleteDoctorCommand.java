package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;


public class DeleteDoctorCommand extends Command{

    public DeleteDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List doctorList) throws NotFoundException {
        doctorList.remove(parameterArray[0]);
        return Status.DELETE_DOCTOR_SUCCESS;
    }
}
