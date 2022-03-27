package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.HalpmiException;
import seedu.duke.status.Status;

public class FindPatientCommand extends Command {

    public FindPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List patientList) {
        switch (parameterArray[0]) {
        case "nric":
            patientList.findByNric(parameterArray);
            break;
        case "name":
            patientList.findByName(parameterArray);
            break;
        case "age":
            patientList.findByAge(parameterArray);
            break;
        case "gender":
            patientList.findByGender(parameterArray);
            break;
        case "address":
            patientList.findByAddress(parameterArray);
            break;
        case "dob":
            patientList.findByDob(parameterArray);
            break;
        case "admissiondate":
            patientList.findByDateAdmission(parameterArray);
            break;
        default:
            break;
        }
        return Status.FIND_PATIENT_SUCCESS;
    }
}
