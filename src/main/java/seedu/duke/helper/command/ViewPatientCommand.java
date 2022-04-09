package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class ViewPatientCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public ViewPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in an PatientList that needs to be acted on.
     * parameterArray should be NULL. Prints list of existing Patients.
     * @ param list a List object - should be an instance of PatientList
     * @ return Status.VIEW_SUCCESS if list was printed.
     * @ throws HalpmiException if issues with parameterArray.
     */
    public Status execute(List patientList) throws UserInputErrorException {
        if (parameterArray == null) {
            patientList.view();
        } else {
            throw new UserInputErrorException("View Patient Command only accepts null parameters!");
        }
        return Status.VIEW_SUCCESS;
    }
}
