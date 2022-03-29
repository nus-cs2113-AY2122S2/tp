package tp;

public class ExitCommand extends Command {
    public ExitCommand() {

    }


    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage storage) throws IHospitalException {
        return;
    }
}
