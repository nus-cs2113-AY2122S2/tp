package tp;

public class AddPatientDescriptionCommand extends Command {
    protected static String description;
    protected static int index;


    public AddPatientDescriptionCommand(String description, int index) {
        this.description = description;
        this.index = index;
    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        PatientList.addPatientDescription(description,index);
        return String.format("Already add description for patient " + index + " \n");
    }
}
