package seedu.duke.helper.command;

import seedu.duke.assets.Appointment;
import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.DoctorList;
import seedu.duke.assets.List;
import seedu.duke.assets.PatientList;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class DeleteAppointmentCommand extends Command {
    String[] newArray = new String[3];
    
    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public DeleteAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    /*
     * Method that takes in an AppointmentList that needs to be acted on.
     * Uses parameterArray as inputs to delete an existing Appointment in the List.
     * @ param list a List object - should be an instance of AppointmentList
     * @ return Status.DELETE_APPOINTMENT_SUCCESS if changes made to list was successful
     * @ throws NotFoundException if entry does not exist.
     */
    public Status execute(List appointmentList) throws NotFoundException {
        if (appointmentList instanceof AppointmentList) {
            String info = ((AppointmentList) appointmentList).appointmentInformation(parameterArray[0]);
            appointmentList.remove(parameterArray[0]);
            newArray = info.split(",");
        }
        if (appointmentList instanceof PatientList) {
            System.out.println(newArray[1]);
            ((PatientList) appointmentList).removeAppointmentDate(newArray[1],newArray[2]);
        }
        if (appointmentList instanceof DoctorList) {
            ((DoctorList) appointmentList).removeAppointmentDate(newArray[0],newArray[2]);
        }
        return Status.DELETE_APPOINTMENT_SUCCESS;
    }
}
