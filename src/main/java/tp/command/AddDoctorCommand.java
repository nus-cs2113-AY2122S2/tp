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

public class AddDoctorCommand extends Command {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected int wardNumber;
    protected String department;
    protected boolean isOnDuty;

    public AddDoctorCommand() {
    }

    //@@author Demonshaha
    public AddDoctorCommand(String id, String name, String phoneNumber, String email, String department,
                            int wardNumber, boolean isOnDuty) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.wardNumber = wardNumber;
        this.department = department;
        this.isOnDuty = isOnDuty;
    }

    //@@author Demonshaha
    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Doctor doctor = new Doctor(id, name, phoneNumber, email, department, wardNumber);
        doctorList.addDoctor(doctor);

        //@@author cczhouqi
        return (boundary + "Noted. I've added this doctor:"
                        + "\n" + doctorList.getDoctor(doctorList.getSize())
                        + "\n" + "Now you have " + doctorList.getSize()
                        + " doctors recorded in the system." + System.lineSeparator() + boundary);
    }
}
