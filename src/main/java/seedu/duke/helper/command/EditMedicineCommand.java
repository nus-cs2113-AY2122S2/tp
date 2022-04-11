package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class EditMedicineCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public EditMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in an MedicineList that needs to be acted on.
     * Uses parameterArray as inputs to edit an existing Medicine in the List.
     * @ param list a List object - should be an instance of MedicineList
     * @ return Status.EDIT_MEDICINE_SUCCESS if changes made to list was successful
     * @ throws NotFoundException if entry does not exist.
     */
    @Override
    public Status execute(List medicineList) throws NotFoundException {
        medicineList.edit(parameterArray);
        return Status.EDIT_MEDICINE_SUCCESS;
    }
}
