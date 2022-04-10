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

//@@author DolphXty
public class AddNurseCommand extends Command {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String title;
    protected int wardNumber;
    protected boolean isOnDuty;

    public AddNurseCommand() {
    }

    public AddNurseCommand(String id, String name, String phoneNumber, String email, String title,
                           int wardNumber, boolean isOnDuty) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.wardNumber = wardNumber;
        this.isOnDuty = isOnDuty;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Nurse nurse = new Nurse(id, name, phoneNumber, email, title, wardNumber);
        nurseList.addNurse(nurse);
        return (boundary + "Noted. I've added this nurse:"
                        + "\n" + nurseList.getNurse(nurseList.getSize())
                        + "\n" + "Now you have " + nurseList.getSize()
                        + " nurses recorded in the system." + System.lineSeparator() + boundary);
    }
}
