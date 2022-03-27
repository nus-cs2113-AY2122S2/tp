package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.HalpmiException;
import seedu.duke.status.Status;

public class ViewDoctorCommand extends Command {

    public ViewDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List doctorList) throws HalpmiException {
        if (parameterArray == null) {
            doctorList.view();
        } else {
            doctorList.view(parameterArray[0]);
        }
        return Status.VIEW_SUCCESS;
    }
}
