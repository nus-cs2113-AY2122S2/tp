package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

import java.util.Arrays;

/*
 * Class that extends Abstract Command Class.
 */
public class DispenseMedicineCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public DispenseMedicineCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in  MedicineList or AppointmentList that needs to be acted on.
     * Uses parameterArray as inputs to shift required medicines from medicine inventory to Appointment.
     * @ param list a List object - should be an instance of MedicineList/AppointmentList
     * @ return Status.DISPENSE_SUCCESS if changes made to list was successful
     * Also prints out table of medicines to dispense.
     */
    @Override
    public Status execute(List list) throws DuplicateEntryException, NotFoundException, UserInputErrorException {
        String patientNric = parameterArray[0];
        String[] medicineArray = Arrays.copyOfRange(parameterArray, 1, parameterArray.length);
        if (list instanceof AppointmentList) {
            ((AppointmentList) list).dispenseMedicine(patientNric,medicineArray);
            return Status.DISPENSE_SUCCESS;
        }
        if (list instanceof MedicineList) {
            ((MedicineList) list).dispenseMedicine(medicineArray);
            return Status.DISPENSE_SUCCESS;
        }
        assert false;
        throw new NotFoundException("Error in code, approach developer!");
    }
}
