package tp.command;

import tp.AppointmentList;
import tp.AppointmentStorage;
import tp.DoctorList;
import tp.DoctorStorage;
import tp.IHospitalException;
import tp.NurseList;
import tp.NurseStorage;
import tp.PatientList;
import tp.PatientStorage;
import tp.Ui;
import tp.WardList;
import tp.WardStorage;
import tp.person.Nurse;

public class DeleteNurseCommand extends Command {
    private final int index;

    public DeleteNurseCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index <= 0 || index > nurseList.getSize()) {
            throw new IHospitalException("The nurse does not exist.\n");
        }
        Nurse cur = nurseList.deleteNurse(index);
        return String.format(boundary + "Noted. I've removed this nurse:" + cur
                + "\n" + "Now you have " + nurseList.getSize()
                + " nurses in the system." + System.lineSeparator() + boundary);
    }
}
