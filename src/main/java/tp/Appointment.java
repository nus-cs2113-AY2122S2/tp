package tp;

import tp.person.Doctor;
import tp.person.Patient;

import java.time.LocalDateTime;

public class Appointment {
    protected Doctor doctor;
    protected Patient patient;
    protected LocalDateTime time;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime time) {
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void editDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void editPatient(Patient patient) {
        this.patient = patient;
    }

    public void editTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Doctor: " + doctor.getName() + " || Patient: "
                       + patient.getName() + " || Appointment time: " + time;
    }
}
