package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class DeletePatientCommand extends Command{

    public DeletePatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List patientList) throws NotFoundException {
        patientList.remove(parameterArray[0]);
        return Status.DELETE_PATIENT_SUCCESS;
    }
}
