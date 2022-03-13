package tp;

import tp.person.Doctor;
import tp.person.Patient;

public class AddPatientCommand extends Command {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected boolean isOnDuty;

    public AddPatientCommand() {
    }

    public AddPatientCommand(String id, String name, String phoneNumber, String email, boolean isOnDuty) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isOnDuty = isOnDuty;
    }

    /**
     *
     * @param doctorList Doctors' list
     * @param patientList  Patients' list
     * @param appointmentList Appointments' list
     * @param ui IHospital's ui
     * @param storage IHospital's storage
     * @return
     * @throws IHospitalException
     */

    @Override
    public void execute(DoctorList doctorList, PatientList patientList, AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        Patient patient = new Patient(id, name, phoneNumber, email);
        patientList.addPatient(patient);
    }
}
