package tp;

public class ListPatientListCommand extends Command {

    @Override
    public void execute(DoctorList doctorList, PatientList patientList, AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        patientList.printPatientList();
    }
}
