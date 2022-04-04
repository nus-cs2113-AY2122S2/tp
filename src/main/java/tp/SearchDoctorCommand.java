package tp;

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
            return String.format("There is no doctor id is " + id + "\n");
        }
        return String.format("The doctor founded is here" + curr + "\n");
    }
}
