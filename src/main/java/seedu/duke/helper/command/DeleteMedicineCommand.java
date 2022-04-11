package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class DeleteMedicineCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public DeleteMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in a MedicineList that needs to be acted on.
     * Uses parameterArray as inputs to delete an existing Medicine in the List.
     * @ param list a List object - should be an instance of MedicineList
     * @ return Status.DELETE_MEDICINE_SUCCESS if changes made to list was successful
     * @ throws NotFoundException if entry does not exist.
     */
    public Status execute(List medicineList) throws NotFoundException {
        medicineList.remove(parameterArray[0]);
        return Status.DELETE_MEDICINE_SUCCESS;
    }
}
