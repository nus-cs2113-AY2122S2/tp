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
import tp.person.Doctor;

//@@author Demonshaha
public class DeleteDoctorCommand extends Command {
    private final int index;

    public DeleteDoctorCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {

        if (index <= 0 || index > doctorList.getSize()) {
            throw new IHospitalException("The doctor does not exist.\n");
        }

        int wardNumber = doctorList.getDoctor(index).getWardNumber();

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

        Doctor curr = doctorList.deleteDoctor(index);

        //@@author cczhouqi
        return (boundary + "Noted. I've removed this doctor:\n" + curr
                        + "\n" + "Now you have " + doctorList.getSize()
                        + " doctors in the system." + System.lineSeparator() + boundary);
    }
}
