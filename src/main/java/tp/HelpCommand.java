package tp;

public class HelpCommand extends Command {
    public HelpCommand() {

    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                        PatientStorage patientStorage,
                        AppointmentStorage appointmentStorage) throws IHospitalException {
        ui.printHelpPage();
        return null;
    }
}
