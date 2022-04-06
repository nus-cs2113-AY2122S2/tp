package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.Appointment;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;

public class SearchAppointmentCommand extends Command {
    protected String time;

    public SearchAppointmentCommand(String time) {
        this.time = time;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Appointment curr = appointmentList.searchAppointmentByTime(time);
        if (curr == null) {
            return String.format("There is no appointment at " + time + "\n");
        }
        return String.format("The appointments found are here" + curr + "\n");
    }
}