package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class EditDoctorCommand extends Command{
    public EditDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List doctorList) throws NotFoundException {
        doctorList.edit(parameterArray);
        return Status.EDIT_DOCTOR_SUCCESS;
    }
}
