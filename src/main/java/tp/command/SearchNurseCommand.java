package tp.command;

import tp.*;
import tp.person.Nurse;

public class SearchNurseCommand extends Command{
    protected String id;

    public SearchNurseCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Nurse cur = nurseList.searchNurse(id);
        if (cur == null) {
            return String.format("There is no doctor with ID: " + id + "\n");
        }
        return String.format("Here's the doctor found: \n" + cur + "\n");
    }
}
