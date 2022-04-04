package tp;

public class GetAppointmentsOfDoctorCommand extends Command {
    private final String id;

    public GetAppointmentsOfDoctorCommand(String id) {
        this.id = id;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        return appointmentList.getAppointmentListOfDoctorById(id).toString();
    }
}
