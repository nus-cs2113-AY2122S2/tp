package seedu.duke.helper.command;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class FindDoctorCommand extends Command {
    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public FindDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }


    public Status execute(List doctorList) {
        if (doctorList instanceof DoctorList) {
            switch (parameterArray[0].toLowerCase()) {
            case "nric":
                ((DoctorList) doctorList).findByNric(parameterArray);
                break;
            case "name":
                ((DoctorList) doctorList).findByName(parameterArray);
                break;
            case "age":
                ((DoctorList) doctorList).findByAge(parameterArray);
                break;
            case "gender":
                ((DoctorList) doctorList).findByGender(parameterArray);
                break;
            case "address":
                ((DoctorList) doctorList).findByAddress(parameterArray);
                break;
            case "dob":
                ((DoctorList) doctorList).findByDob(parameterArray);
                break;
            case "specialization":
                ((DoctorList) doctorList).findBySpecialization(parameterArray);
                break;
            default:
                break;
            }
        }
        return Status.FIND_DOCTOR_SUCCESS;
    }
}
