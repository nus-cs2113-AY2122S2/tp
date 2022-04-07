package tp.command;

import tp.*;
import tp.person.Patient;

public class SearchPatientCommand extends Command {
    protected String id;

    public SearchPatientCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Patient curr = patientList.searchPatient(id);
        if (curr == null) {
            return String.format("There is no patient with ID: " + id + "\n");
        }
        return String.format("Here's the patient found: \n" + curr + "\n");
    }
}
