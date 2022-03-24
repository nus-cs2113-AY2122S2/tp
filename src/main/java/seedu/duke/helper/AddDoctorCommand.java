package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;

public class AddDoctorCommand extends Command{

    AddDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public void execute(List doctorList) throws DuplicateEntryException {
        doctorList.add(parameterArray);
    }

}
