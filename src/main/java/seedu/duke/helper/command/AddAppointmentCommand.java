package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;
import seedu.duke.assets.DoctorList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class AddAppointmentCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public AddAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in an AppointmentList that needs to be acted on.
     * Uses parameterArray as inputs to add a new Appointment to the List.
     * @ param list a List object - should be an instance of AppointmentList
     * @ return Status.ADD_APPOINTMENT_SUCCESS if changes made to list was successful
     * @ throws DuplicateEntryException if similar entry already exist.
     */
    @Override
    public Status execute(List appointmentList) throws DuplicateEntryException, UserInputErrorException {
        if(appointmentList instanceof AppointmentList) {
            appointmentList.add(parameterArray);
        } else if(appointmentList instanceof PatientList) {
            ((PatientList) appointmentList).addAppointmentDate(parameterArray[0], parameterArray[2]);
        } else if(appointmentList instanceof DoctorList) {
            ((DoctorList) appointmentList).addAppointmentDate(parameterArray[1], parameterArray[2]);
        }
        return Status.ADD_APPOINTMENT_SUCCESS;
    }
}
