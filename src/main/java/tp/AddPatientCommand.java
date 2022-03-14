package tp;

import tp.person.Doctor;
import tp.person.Patient;

public class AddPatientCommand extends Command {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;

    public AddPatientCommand() {
    }

    public AddPatientCommand(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        Patient patient = new Patient(id, name, phoneNumber, email);
        patientList.addPatient(patient);
    }
}
