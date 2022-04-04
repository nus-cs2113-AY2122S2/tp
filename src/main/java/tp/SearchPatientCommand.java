package tp;

import tp.person.Patient;

public class SearchPatientCommand extends Command{
    protected String id;
    public SearchPatientCommand(String id){
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        Patient curr = patientList.searchPatient(id);
        if(curr == null) {
            return String.format("There is no patient id is " + id + "\n");
        }
        return String.format("The patient founded is here" + curr + "\n");
    }
}
