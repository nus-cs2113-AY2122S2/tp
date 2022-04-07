package tp.command;

import tp.*;

public class SortAppointmentByTimeCommand extends Command {

    public SortAppointmentByTimeCommand() {

    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        appointmentList.sortByTime();
        return String.format("Sorted already !" + "\n");
    }
}
