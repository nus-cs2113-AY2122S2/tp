package seedu.duke.assets;

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
            System.out.println("There is no such patient");
            return;
        }
        System.out.println(getPatient(nric).toString());
    }

    public void viewPatient() {
        System.out.println(patients.toString());
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

    @Override
    public String toString() {
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
