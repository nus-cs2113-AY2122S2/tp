package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class ViewAppointmentCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public ViewAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in an AppointmentList that needs to be acted on.
     * parameterArray should be NULL. Prints list of existing Appointments.
     * @ param list a List object - should be an instance of AppointmentList
     * @ return Status.VIEW_SUCCESS if list was printed.
     * @ throws HalpmiException if issues with parameterArray.
     */
    public Status execute(List appointmentList) throws UserInputErrorException {
        if (parameterArray == null) {
            appointmentList.view();
        } else {
            throw new UserInputErrorException("View Appointment Command only accepts null parameters!");
        }
        return Status.VIEW_SUCCESS;
    }
}
