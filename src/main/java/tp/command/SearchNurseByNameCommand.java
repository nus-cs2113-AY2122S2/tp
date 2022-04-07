package tp.command;

import tp.*;
import tp.person.Nurse;

public class SearchNurseByNameCommand extends Command{
    protected String name;

    public SearchNurseByNameCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Nurse cur = nurseList.searchNurseByName(name);
        if (cur == null) {
            return String.format("There is no doctor with ID: " + name + "\n");
        }
        return String.format("Here's the doctor found: \n" + cur + "\n");
    }
}
