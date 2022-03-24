package seedu.duke.helper;

import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.NotFoundException;

public class DeletePatientCommand extends Command{

    DeletePatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List patientList) throws NotFoundException {
        patientList.remove(parameterArray[0]);
    }
}
