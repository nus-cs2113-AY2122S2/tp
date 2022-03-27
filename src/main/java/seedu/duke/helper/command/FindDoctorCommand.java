package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.HalpmiException;
import seedu.duke.status.Status;

public class FindDoctorCommand extends Command {

    public FindDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List doctorList) throws HalpmiException {
        switch (parameterArray[0]) {
        case "nric":
            doctorList.findByNric(parameterArray);
            break;
        case "name":
            doctorList.findByName(parameterArray);
            break;
        case "age":
            doctorList.findByAge(parameterArray);
            break;
        case "gender":
            doctorList.findByGender(parameterArray);
            break;
        case "address":
            doctorList.findByAddress(parameterArray);
            break;
        case "dob":
            doctorList.findByDob(parameterArray);
            break;
        case "specialization":
            doctorList.findBySpecialization(parameterArray);
            break;
        default:
            throw new HalpmiException("Input must be an attribute of Doctor");
        }
        return Status.FIND_DOCTOR_SUCCESS;
    }
}
