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
        return patients.get(index - 1);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        countPatient++;

        System.out.println(boundary + "Noted. I've added this patient:");
        System.out.println(patients.get(countPatient - 1));
        System.out.print("Now you have " + countPatient
                                 + " patients recorded in the system." + System.lineSeparator() + boundary);
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
        patients.remove(index - 1);
        countPatient -= 1;
    }

    @Override
    public String toString() {
        String toPrint = boundary + "Here are the patients recorded:" + System.lineSeparator();
        for (int i = 0; i < countPatient; i++) {
            toPrint += ((i + 1) + ". " + getPatient(i) + System.lineSeparator());
        }
        toPrint += ("You have " + countPatient + " patients recorded in the system."
                            + System.lineSeparator() + boundary + System.lineSeparator());
        return toPrint;
    }
}

