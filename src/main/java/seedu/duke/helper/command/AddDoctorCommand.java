package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.status.Status;

public class AddDoctorCommand extends Command{

    public AddDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List doctorList) throws DuplicateEntryException {
        doctorList.add(parameterArray);
        return Status.ADD_DOCTOR_SUCCESS;
    }

}
