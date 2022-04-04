package tp;

public class SearchAppointmentCommand extends Command {
    protected String time;

    public SearchAppointmentCommand(String time) {
        this.time = time;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        Appointment curr = appointmentList.searchAppointmentByTime(time);
        if (curr == null) {
            return String.format("There is no appointment at " + time + "\n");
        }
        return String.format("The appointments founded are here" + curr + "\n");
    }
}