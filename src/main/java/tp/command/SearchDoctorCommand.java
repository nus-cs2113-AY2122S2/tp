package tp.command;

import tp.*;
import tp.person.Doctor;

public class SearchDoctorCommand extends Command {
    protected String id;

    public SearchDoctorCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Doctor curr = doctorList.searchDoctor(id);
        if (curr == null) {
            return String.format("There is no doctor with ID: " + id + "\n");
        }
        return String.format("Here's the doctor found: \n" + curr + "\n");
    }
}
