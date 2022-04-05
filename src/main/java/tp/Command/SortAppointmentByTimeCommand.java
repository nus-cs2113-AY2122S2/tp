package tp.Command;

import tp.*;

public class SortAppointmentByTimeCommand extends Command {

    public SortAppointmentByTimeCommand() {

    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        appointmentList.sortByTime();
        return String.format("Sorted already !" + "\n");
    }
}
