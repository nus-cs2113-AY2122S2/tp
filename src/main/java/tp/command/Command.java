package tp.command;

import tp.*;

public abstract class Command {
    public static String boundary = "____________________________________________________________\n";

    public boolean isExit() {
        return false;
    }

    public abstract String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                                   AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                                   PatientStorage patientStorage,NurseStorage nurseStorage,
                                   AppointmentStorage appointmentStorage) throws IHospitalException;
}
