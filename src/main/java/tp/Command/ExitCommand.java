package tp.Command;

import tp.*;

public class ExitCommand extends Command {
    public ExitCommand() {

    }


    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        return null;
    }
}
