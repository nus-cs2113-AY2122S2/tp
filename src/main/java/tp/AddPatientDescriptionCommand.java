package tp;

public class AddPatientDescriptionCommand extends Command{
    protected String description;
    protected String  index;


    public AddPatientDescriptionCommand(String description, String index) {
        this.description=description;
        this.index=index;
    }


    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        PatientList.addPatientDescription(description,index);
    }
}
