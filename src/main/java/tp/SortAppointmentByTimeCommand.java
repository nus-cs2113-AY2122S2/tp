package tp;

public class SortAppointmentByTimeCommand extends Command {

    public SortAppointmentByTimeCommand() {

    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        appointmentList.sortByTime();
    }
}
