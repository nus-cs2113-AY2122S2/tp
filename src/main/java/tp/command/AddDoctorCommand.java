package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.person.Doctor;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;

public class AddDoctorCommand extends Command {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected boolean isOnDuty;

    public AddDoctorCommand() {
    }

    public AddDoctorCommand(String id, String name, String phoneNumber, String email, boolean isOnDuty) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isOnDuty = isOnDuty;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Doctor doctor = new Doctor(id, name, phoneNumber, email);
        doctorList.addDoctor(doctor);
        return String.format(boundary + "Noted. I've added this doctor:"
                + "\n" + doctorList.getDoctor(doctorList.getSize())
                + "\n" + "Now you have " + doctorList.getSize()
                + " doctors recorded in the system." + System.lineSeparator() + boundary);
    }
}
