package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class EditDoctorCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public EditDoctorCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in a DoctorList that needs to be acted on.
     * Uses parameterArray as inputs to edit an existing Doctor in the List.
     * @ param list a List object - should be an instance of DoctorList
     * @ return Status.EDIT_DOCTOR_SUCCESS if changes made to list was successful
     * @ throws NotFoundException if entry does not exist.
     */
    @Override
    public Status execute(List doctorList) throws NotFoundException {
        doctorList.edit(parameterArray);
        return Status.EDIT_DOCTOR_SUCCESS;
    }
}
