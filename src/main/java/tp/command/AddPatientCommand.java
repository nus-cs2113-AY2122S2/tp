package tp.command;

import tp.AppointmentList;
import tp.DoctorList;
import tp.PatientList;
import tp.Ui;
import tp.person.Patient;
import tp.DoctorStorage;
import tp.PatientStorage;
import tp.IHospitalException;
import tp.AppointmentStorage;

public class AddPatientCommand extends Command {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String symptom;
    protected String description;

    public AddPatientCommand() {
    }

    public AddPatientCommand(String id, String name, String phoneNumber,
                             String email, String symptom, String description) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.symptom = symptom;
        this.description = description;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Patient patient = new Patient(id, name, phoneNumber, email,symptom,description);
        patientList.addPatient(patient);
        return String.format(boundary + "Noted. I've added this patient:" + "\n"
                + patientList.getPatient(patientList.getSize()) + "\n"
                + "Now you have " + patientList.getSize()
                + " patients recorded in the system." + System.lineSeparator() + boundary);
    }
}
