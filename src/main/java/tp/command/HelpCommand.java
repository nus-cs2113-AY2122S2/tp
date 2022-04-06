package tp.command;

import tp.*;

public class HelpCommand extends Command {
    public HelpCommand() {

    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        ui.printHelpPage();
        return null;
    }
}
