package tp;

import tp.person.Doctor;

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
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        Doctor doctor = new Doctor(id, name, phoneNumber, email);
        doctorList.addDoctor(doctor);
    }
}
