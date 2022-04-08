package tp.command;

import tp.*;

public class SearchWardCommand extends Command{
    protected String id;

    public SearchWardCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, WardList wardList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage, WardStorage wardStorage) throws IHospitalException {
        Ward cur = wardList.searchWard(id);
        if (cur == null) {
            return String.format("There is no ward with ID: " + id + "\n");
        }
        return String.format("Here's the ward found: \n" + cur + "\n");
    }
}