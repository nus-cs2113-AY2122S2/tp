package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class ViewMedicineCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public ViewMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in an MedicineList that needs to be acted on.
     * parameterArray should be NULL. Prints list of existing Medicines.
     * @ param list a List object - should be an instance of MedicineList
     * @ return Status.VIEW_SUCCESS if list was printed.
     * @ throws HalpmiException if issues with parameterArray.
     */
    public Status execute(List medicineList) throws UserInputErrorException {
        if (parameterArray == null) {
            medicineList.view();
        } else {
            medicineList.view(parameterArray[0]);
        }
        return Status.VIEW_SUCCESS;
    }
}
