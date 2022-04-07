package seedu.duke.helper.command;

import seedu.duke.assets.Doctor;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.finder.DoctorFinder;
import seedu.duke.status.Status;

import java.util.ArrayList;

public class CheckIfDoctorExistsCommand extends Command {
    public CheckIfDoctorExistsCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List doctorList) throws NotFoundException {
        String inputDoctorNric = parameterArray[1];
        DoctorFinder doctorFinder = new DoctorFinder();
        ArrayList<Doctor> foundDoctor = doctorFinder.findDoctorByNric(((DoctorList)doctorList).getList(),
                inputDoctorNric);
        if (foundDoctor == null) {
            throw new NotFoundException("There is no doctor with the NRIC given. Please try again.");
        }
        return Status.FIND_DOCTOR_SUCCESS;
    }
}
