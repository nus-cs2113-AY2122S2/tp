package tp.command;

import tp.*;
import tp.person.Nurse;
import tp.NurseList;

public class AddNurseCommand extends Command{
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String title;
    protected boolean isOnDuty;

    public AddNurseCommand() {
    }

    public AddNurseCommand(String id, String name, String phoneNumber, String email, String title, boolean isOnDuty) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title=title;
        this.isOnDuty = isOnDuty;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Nurse nurse = new Nurse(id, name, phoneNumber, email,title);
        nurseList.addNurse(nurse);
        return String.format(boundary + "Noted. I've added this doctor:"
                + "\n" + nurseList.getNurse(nurseList.getSize())
                + "\n" + "Now you have " + nurseList.getSize()
                + " doctors recorded in the system." + System.lineSeparator() + boundary);
    }
}
