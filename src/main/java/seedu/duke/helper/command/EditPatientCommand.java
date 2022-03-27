package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class EditPatientCommand extends Command {
    public EditPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List patientList) throws NotFoundException {
        patientList.edit(parameterArray);
        return Status.EDIT_PATIENT_SUCCESS;
    }
}

