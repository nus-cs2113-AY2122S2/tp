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

//@@author sethlxk
public class EditNurseCommand extends Command {
    private int index;
    private String type;
    private String newInformation;

    public EditNurseCommand(int index, String type, String newInformation) {
        this.index = index;
        this.type = type;
        this.newInformation = newInformation;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        if (index > nurseList.getSize()) {
            throw new IHospitalException("The nurse does not exist\n");
        }

        if (type.contains("e")) {
            nurseList.getNurse(index).editEmail(newInformation);
            return String.format(boundary + "Updated already!\n" + nurseList.getNurse(index) + "\n" + boundary);
        } else if (type.contains("ph")) {
            nurseList.getNurse(index).editPhoneNumber(newInformation);
            return String.format(boundary + "Updated already!\n" + nurseList.getNurse(index) + "\n" + boundary);
        } else if (type.contains("t")) {
            nurseList.getNurse(index).editTitle(newInformation);
            return String.format(boundary + "Updated already!\n" + nurseList.getNurse(index) + "\n" + boundary);
        } else {
            nurseList.getNurse(index).editName(newInformation);
            return String.format(boundary + "Updated already!\n" + nurseList.getNurse(index) + "\n" + boundary);
        }
    }
}
