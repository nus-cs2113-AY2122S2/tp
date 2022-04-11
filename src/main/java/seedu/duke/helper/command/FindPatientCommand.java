package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class FindPatientCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public FindPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List patientList) {
        if (patientList instanceof PatientList) {
            switch (parameterArray[0].toLowerCase()) {
            case "nric":
                ((PatientList) patientList).findByNric(parameterArray);
                break;
            case "name":
                ((PatientList) patientList).findByName(parameterArray);
                break;
            case "age":
                ((PatientList) patientList).findByAge(parameterArray);
                break;
            case "gender":
                ((PatientList) patientList).findByGender(parameterArray);
                break;
            case "address":
                ((PatientList) patientList).findByAddress(parameterArray);
                break;
            case "dob":
                ((PatientList) patientList).findByDob(parameterArray);
                break;
            case "registrationdate":
                ((PatientList) patientList).findByDateAdmission(parameterArray);
                break;
            default:
                break;
            }
        }
        return Status.FIND_PATIENT_SUCCESS;
    }
}
