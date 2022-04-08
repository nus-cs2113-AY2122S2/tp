package tp;

import org.junit.jupiter.api.Test;
import tp.IHospitalException;
import tp.PatientList;
import tp.Ui;
import tp.person.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PatientListTest {
    private final Patient firstPatient = new Patient("1234", "john",
            "12341234", "3600@gmail.com", "symptom", "description");
    private final Patient secondPatient = new Patient("0299", "Chris",
            "55556666", "jmchris@gmail.com", "cough", "Allergic to Penicillin");
    private Ui ui = new Ui();

    @Test
    void getPatient() {
        PatientList patients = new PatientList();

        try {
            patients.addPatient(firstPatient);
            patients.addPatient(secondPatient);
            assertEquals(secondPatient, patients.getPatient(2));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void getSize() {
        PatientList patients = new PatientList();

        try {
            patients.addPatient(firstPatient);
            patients.addPatient(secondPatient);
            assertEquals(2, patients.getSize());
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void searchPatient() {
        PatientList patients = new PatientList();

        try {
            patients.addPatient(firstPatient);
            patients.addPatient(secondPatient);
            assertEquals(secondPatient, patients.searchPatient("0299"));
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }

    @Test
    void testToString() {
        PatientList patients = new PatientList();

        String toPrint = "____________________________________________________________\n"
                                 + "Here are the patients recorded:\n"
                                 + "1. * PATIENT [1234] || Name: john || Contact No.: 12341234 || Email: 3600@gmail.com "
                                 + "|| Symptom: symptom || Description: description\n"
                                 + "2. * PATIENT [0299] || Name: Chris || Contact No.: 55556666 || Email: jmchris@gmail.com "
                                 + "|| Symptom: cough || Description: Allergic to Penicillin\n"
                                 + "You have 2 patients recorded in the system.\n"
                                 + "____________________________________________________________\n";
        try {
            patients.addPatient(firstPatient);
            patients.addPatient(secondPatient);
            assertEquals(toPrint, patients.toString());
        } catch (IHospitalException e) {
            ui.generateResponse(e.getMessage());
        }
    }
}