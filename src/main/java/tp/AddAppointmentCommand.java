package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.time.LocalDateTime;

public class AddAppointmentCommand extends Command {
    protected int doctorIndex;
    protected int patientIndex;
    protected LocalDateTime time;

    public AddAppointmentCommand() {

    }

    public AddAppointmentCommand(int doctorIndex, int patientIndex, String time) {
        this.doctorIndex = doctorIndex;
        this.patientIndex = patientIndex;
        this.time = LocalDateTime.parse(time);
    }


    @Override
    public void execute(DoctorList doctorList, PatientList patientList,
                        AppointmentList appointmentList, Ui ui, Storage storage) throws IHospitalException {
        Doctor doctor = (Doctor) doctorList.getDoctor(doctorIndex);
        Patient patient = (Patient) patientList.getPatient(patientIndex);
        appointmentList.addAppointment(doctor, patient, time);
    }
}
