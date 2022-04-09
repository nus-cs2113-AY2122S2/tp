package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class CheckIfAppointmentTodayCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public CheckIfAppointmentTodayCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in  AppointmentList that needs to be acted on.
     * Uses parameterArray as inputs to find if Patient has appointment today.
     * @ param list a List object - should be an instance of AppointmentList
     * @ return Status.APPOINTMENT_FOUND_SUCCESS if patient has appointment today.
     * @ throws HalpMiException if patient does not exist or patient does not have appointment today.
     */
    @Override
    public Status execute(List appointmentList) throws DuplicateEntryException, NotFoundException,
            UserInputErrorException {
        if (appointmentList instanceof AppointmentList) {
            ((AppointmentList) appointmentList).hasAppointmentToday("P",parameterArray[0]);
        }
        return Status.APPOINTMENT_FOUND_SUCCESS;
    }
}
