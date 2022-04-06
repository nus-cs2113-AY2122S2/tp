package tp.command;

import tp.*;
import tp.person.Nurse;

public class DeleteNurseCommand extends Command{
    private final int index;

    public DeleteNurseCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > nurseList.getSize()) {
            throw new IHospitalException("The nurse does not exist.\n");
        }

        Nurse cur = nurseList.deleteNurse(index);
        return String.format(boundary + "Noted. I've removed this nurse:" + cur
                + "\n" + "Now you have " + nurseList.getSize()
                + " nurses in the system." + System.lineSeparator() + boundary);
    }
}
