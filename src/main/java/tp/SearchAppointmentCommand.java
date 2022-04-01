package tp;

public class SearchAppointmentCommand extends Command {
    protected String id;

    public SearchAppointmentCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        appointmentList.searchAppointment(id);
    }
}