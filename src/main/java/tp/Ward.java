package tp;

import tp.person.Doctor;
import tp.person.Nurse;
import tp.person.Patient;

public class Ward {
    protected Doctor doctor;
    protected Patient patient;
    protected Nurse nurse;
    protected int wardNumber;


    public Ward(Doctor doctor, Patient patient, Nurse nurse, int wardNumber) {
        this.doctor = doctor;
        this.patient = patient;
        this.nurse = nurse;
        this.wardNumber = wardNumber;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void editDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void editPatient(Patient patient) {
        this.patient = patient;
    }

    public void editNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public int getNumber() {
        return wardNumber;
    }

    public void editNumber(int number) {
        this.wardNumber = number;
    }

    @Override
    public String toString() {
        return   "Doctor: " + doctor.getName() + " || Nurse: "
                + "Nurse: " + nurse.getName() + " || Patient: "
                + patient.getName() + " || Ward number: " + wardNumber;
    }
}

