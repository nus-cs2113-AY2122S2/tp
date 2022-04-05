package tp.Command;

import tp.*;
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
    public String execute(DoctorList doctorList, PatientList patientList,
                          AppointmentList appointmentList, Ui ui, DoctorStorage doctorStorage,
                          PatientStorage patientStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Doctor doctor = (Doctor) doctorList.getDoctor(doctorIndex);
        Patient patient = (Patient) patientList.getPatient(patientIndex);
        appointmentList.addAppointment(doctor, patient, time);
        return String.format(boundary + "Noted. I've added this appointment:\n"
                + appointmentList.getAppointment(appointmentList.getSize()) + "\n"
                + "Now you have " + appointmentList.getSize()
                + " appointments recorded in the system." + System.lineSeparator() + boundary);
    }
}
