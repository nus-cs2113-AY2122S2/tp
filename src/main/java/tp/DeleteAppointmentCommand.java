package tp;

public class DeleteAppointmentCommand extends Command {
    int index;

    public DeleteAppointmentCommand(){

    }

    public DeleteAppointmentCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        Appointment curr = appointmentList.deleteAppointment(index);
        return String.format(boundary + "Noted. I've removed this appointment:" + curr
                + "\n" + "Now you have " + (appointmentList.getSize())
                + " appointments recorded in the system." + System.lineSeparator() + boundary);
    }
}
