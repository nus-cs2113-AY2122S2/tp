package tp;

public class ListAppointmentListCommand extends Command {

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        appointmentList.printAppointmentList();
    }
}
