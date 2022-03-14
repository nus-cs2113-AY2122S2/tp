package tp;

public class ListDoctorListCommand extends Command {

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        doctorList.printDoctorList();
    }
}
