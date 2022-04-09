package seedu.duke.helper.command;

import seedu.duke.assets.AppointmentList;
import seedu.duke.assets.List;
import seedu.duke.status.Status;

/*
 * Class that extends Abstract Command Class.
 */
public class FindAppointmentCommand extends Command {

    /*
     * Constructor method that calls constructor of super class
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public FindAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    public Status execute(List appointmentList) {
        if (appointmentList instanceof AppointmentList) {
            switch (parameterArray[0]) {
            case "id":
                ((AppointmentList) appointmentList).findById(parameterArray);
                break;
            case "patient nric":
                ((AppointmentList) appointmentList).findByPatientNric(parameterArray);
                break;
            case "patient name":
                ((AppointmentList) appointmentList).findByPatientName(parameterArray);
                break;
            case "doctor nric":
                ((AppointmentList) appointmentList).findByDoctorNric(parameterArray);
                break;
            case "doctor name":
                ((AppointmentList) appointmentList).findByDoctorName(parameterArray);
                break;
            case "date":
                ((AppointmentList) appointmentList).findByAppointmentDate(parameterArray);
                break;
            default:
                break;
            }
        }
        return Status.FIND_MEDICINE_SUCCESS;
    }
}