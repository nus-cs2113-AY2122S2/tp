package seedu.duke.assets;

import seedu.duke.helper.UI;

import java.util.ArrayList;

public class PatientList {

    private ArrayList<Patient> patients = new ArrayList<>();

    public Patient getPatient(String nric) {
        for (Patient patient : patients) {
            if (patient.getNric().equals(nric)) {
                return patient;
            }
        }
        return null;
    }

    public void viewPatient(String nric) {
        Patient patient = getPatient(nric);
        if (patient == null) {
            UI.printParagraph("There is no such patient");
            return;
        }
        UI.printParagraph(getPatient(nric).toString());
    }

    public void viewPatient() {
        UI.printParagraph(toString());
    }

    public void add(String[] addPatientParameters) {
        Patient newPatient = new Patient(addPatientParameters[0],addPatientParameters[1],
                Integer.parseInt(addPatientParameters[2]), addPatientParameters[3].charAt(0),
                addPatientParameters[4],addPatientParameters[5], addPatientParameters[6]);
        patients.add(newPatient);
    }

    public int getSize() {
        return patients.size();
    }

    public void removePatient(int index) {
        patients.remove(index);
    }

    public ArrayList<Patient> getList() {
        return patients;
    }

    @Override
    public String toString() {
        if (getSize() == 0) {
            return "There are no patients currently.";
        }
        String output = "";
        int number = 1;
        for (Patient patient : this.patients) {
            output += String.format("%d. %s", number, patient.toString());
            if (number != this.getSize()) {
                output += "\n";
            }
            number++;
        }
        return output;
    }
}
