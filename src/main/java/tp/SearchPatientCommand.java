package tp;

public class SearchPatientCommand extends Command{
    protected String id;
    public SearchPatientCommand(String id){
        this.id = id;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        patientList.searchPatient(id);
    }
}
