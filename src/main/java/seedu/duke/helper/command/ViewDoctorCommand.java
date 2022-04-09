package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class ViewDoctorCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public ViewDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

     /*
     * Method that takes in a DoctorList that needs to be acted on.
     * parameterArray should be NULL. Prints list of existing Doctors.
     * @ param list a List object - should be an instance of DoctorList
     * @ return Status.VIEW_SUCCESS if list was printed.
     * @ throws HalpmiException if issues with parameterArray.
     */
    public Status execute(List doctorList) throws UserInputErrorException {
        if (parameterArray == null) {
            doctorList.view();
        } else {
            doctorList.view(parameterArray[0]);
        }
        return Status.VIEW_SUCCESS;
    }
}
