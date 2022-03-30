package tp;

public class DeleteDoctorCommand extends Command {
    private final int index;

    public DeleteDoctorCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        doctorList.deleteDoctor(index);
    }
}
