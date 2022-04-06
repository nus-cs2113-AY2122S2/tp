package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.person.Doctor;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;
import tp.person.Doctor;

public class SearchDoctorCommand extends Command {
    protected String id;

    public SearchDoctorCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Doctor curr = doctorList.searchDoctor(id);
        if (curr == null) {
            return String.format("There is no doctor with ID: " + id + "\n");
        }
        return String.format("Here's the doctor found: \n" + curr + "\n");
    }
}
