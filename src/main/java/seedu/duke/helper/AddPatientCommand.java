package seedu.duke.helper;

import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.DuplicateEntryException;

public class AddPatientCommand extends Command{

    public AddPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List patientList) throws DuplicateEntryException {
        patientList.add(this.parameterArray);
    }
}
