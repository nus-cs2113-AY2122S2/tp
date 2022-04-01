package tp;

public class SearchDoctorCommand extends Command{
    protected String id;
    public SearchDoctorCommand(String id){
        this.id = id;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        doctorList.searchDoctor(id);
    }
}
