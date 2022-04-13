package tp.command;

import tp.AppointmentList;
import tp.AppointmentStorage;
import tp.DoctorList;
import tp.DoctorStorage;
import tp.IHospitalException;
import tp.NurseList;
import tp.NurseStorage;
import tp.PatientList;
import tp.PatientStorage;
import tp.Ui;
import tp.WardList;
import tp.WardStorage;

//@@author Demonshaha
public abstract class Command {
    public static String boundary = "____________________________________________________________\n";

    public boolean isExit() {
        return false;
    }



    public abstract String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                                   WardList wardList, AppointmentList appointmentList, Ui ui,
                                   DoctorStorage doctorStorage, WardStorage wardStorage,
                                   PatientStorage patientStorage, NurseStorage nurseStorage,
                                   AppointmentStorage appointmentStorage) throws IHospitalException;
}
