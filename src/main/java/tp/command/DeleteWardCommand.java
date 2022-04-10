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
import tp.Ward;

//@@author sethlxk
public class DeleteWardCommand extends Command {
    private final int index;

    public DeleteWardCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index <= 0 || index > wardList.getSize()) {
            throw new IHospitalException("The ward does not exist.\n");
        }
        Ward cur = wardList.deleteWard(index);
        return (boundary + "Noted. I've removed this ward:\n" + cur
                        + "\n" + "Now you have " + wardList.getSize()
                        + " wards in the system." + System.lineSeparator() + boundary);
    }
}
