package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.finder.PatientFinder;
import seedu.duke.status.Status;

import java.util.ArrayList;

public class CheckIfPatientExistsCommand extends Command {
    public CheckIfPatientExistsCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List patientList) throws NotFoundException {
        String inputPatientNric = parameterArray[0];
        PatientFinder patientFinder = new PatientFinder();
        ArrayList<Patient> foundPatient = patientFinder.findPatientByNric(((PatientList)patientList).getList(),
                inputPatientNric);
        if (foundPatient == null) {
            throw new NotFoundException("There is no patient with the NRIC given. Please try again.");
        }
        return Status.FIND_PATIENT_SUCCESS;
    }
}
