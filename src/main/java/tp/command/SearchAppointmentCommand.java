package tp.command;

import tp.Appointment;
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
public class SearchAppointmentCommand extends Command {
    protected String time;

    public SearchAppointmentCommand(String time) {
        this.time = time;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Appointment curr = appointmentList.searchAppointmentByTime(time);
        if (curr == null) {
            return String.format("There is no appointment at " + time + "\n");
        }
        return String.format("The appointments found are here \n" + curr + "\n");
    }
}