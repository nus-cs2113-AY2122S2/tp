package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.status.Status;

public class AddPatientCommand extends Command {

    public AddPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List patientList) throws DuplicateEntryException {
        patientList.add(this.parameterArray);
        return Status.ADD_PATIENT_SUCCESS;
    }
}
