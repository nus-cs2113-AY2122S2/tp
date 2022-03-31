package tp;

public class DeletePatientCommand extends Command {
    private final int index;

    public DeletePatientCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        patientList.deletePatient(index);
    }
}
