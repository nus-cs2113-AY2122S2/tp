package tp.Command;

import tp.*;

public abstract class Command {
    public static String boundary = "____________________________________________________________\n";

    public boolean isExit() {
        return false;
    }

    public abstract String execute(DoctorList doctorList, PatientList patientList,
                                   AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                                   PatientStorage patientStorage,
                                   AppointmentStorage appointmentStorage) throws IHospitalException;
}
