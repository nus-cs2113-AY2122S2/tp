package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;


public class DeleteDoctorCommand extends Command{

    DeleteDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List doctorList) throws NotFoundException {
        doctorList.remove(parameterArray[0]);
    }
}
