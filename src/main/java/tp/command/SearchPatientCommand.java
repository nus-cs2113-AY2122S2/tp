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
import tp.person.Patient;

public class SearchPatientCommand extends Command {
    protected String id;

    public SearchPatientCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Patient curr = patientList.searchPatient(id);
        if (curr == null) {
            return String.format("There is no patient with ID: " + id + "\n");
        }
        return String.format("Here's the patient found: \n" + curr + "\n");
    }
}
