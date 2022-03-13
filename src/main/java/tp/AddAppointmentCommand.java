package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.time.LocalDateTime;

public class AddAppointmentCommand extends Command {
    protected Doctor doctor;
    protected Patient patient;
    protected LocalDateTime time;

    public AddAppointmentCommand() {}
    public AddAppointmentCommand(Doctor doctor, Patient patient, LocalDateTime time) {
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
    }
    @Override
    public void execute(DoctorList doctorList, PatientList patientList, AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        appointmentList.addAppointment(doctor, patient, time);
    }
}
