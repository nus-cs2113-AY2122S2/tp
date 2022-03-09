package tp;

import tp.person.Patient;
import tp.person.Person;

import java.util.ArrayList;

public class PatientList {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();
    protected ArrayList<Patient> patients = new ArrayList<>();
    protected int countPatient;

    public PatientList() {
        countPatient = 0;
    }

    public Person getPatient(int index) {
        return patients.get(index);
    }

    /**
     * Adds a patient to the patient list.
     *
     * @param name Name of the patient.
     * @param phoneNumber Phone number of the patient.
     * @param email Email address of the patient.
     */
    public void addPatient(String name, String phoneNumber, String email) {
        patients.add(new Patient(countPatient + 1, name, phoneNumber, email));
        countPatient++;

        System.out.println(boundary + "Noted. I've added this patient:");
        System.out.println(patients.get(countPatient - 1));
        System.out.print("Now you have " + countPatient
                                 + " patients recorded in the system." + System.lineSeparator() + boundary);
    }

    /**
     * Prints the current list of patients.
     */
    public void printPatientList() {
        System.out.println(boundary + "Here are the patients recorded:");
        for (int i = 0; i < countPatient; i++) {
            System.out.println((i + 1) + ". " + getPatient(i));
        }
        System.out.print("You have " + countPatient + " patients recorded in the system."
                                 + System.lineSeparator() + boundary);
    }

    /**
     * Deletes a patient from the list.
     *
     * @param index Index of the patient to be deleted.
     */
    public void deletePatient(int index) {
        System.out.println(boundary + "Noted. I've removed this patient:");
        System.out.println(patients.get(index));
        System.out.print("Now you have " + (countPatient - 1)
                                 + " patients recorded in the system." + System.lineSeparator() + boundary);
        patients.remove(index);
        countPatient -= 1;
    }
}

