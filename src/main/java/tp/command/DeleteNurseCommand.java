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
        int wardNumber = cur.getWardNumber();

        for (int i = 1; i <= doctorList.getSize(); i++) {
            if (doctorList.getDoctor(i).getWardNumber() == wardNumber) {
                doctorList.getDoctor(i).setWardNumber(-1);
            }
        }

        for (int i = 1; i <= nurseList.getSize(); i++) {
            if (nurseList.getNurse(i).getWardNumber() == wardNumber) {
                nurseList.getNurse(i).setWardNumber(-1);
            }
        }

        for (int i = 1; i <= wardList.getSize(); i++) {
            if (wardNumber == -1) {
                break;
            }
            if (wardList.getWard(i).getNumber() == wardNumber) {
                wardList.deleteWard(i);
            }
        }
        return (boundary + "Noted. I've removed this nurse:\n" + cur
                        + "\n" + "Now you have " + nurseList.getSize()
                        + " nurses in the system." + System.lineSeparator() + boundary);
    }
}
