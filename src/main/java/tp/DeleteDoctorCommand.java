package tp;

public class DeleteDoctorCommand extends Command {
    private final int index;

    public DeleteDoctorCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList, AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        doctorList.deleteDoctor(index);
    }
}
