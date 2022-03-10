package seedu.duke.assets;

import java.util.ArrayList;

public class PatientList {

    ArrayList<Patient> patientList;


    public Patient getPatient(String nric) {
        for (Patient patient : patientList) {
            if (patient.getNric().equals(nric)) {
                return patient;
            }
        }
        return null;
    }

    //view particular patient
    public void viewPatient(String nric) {
        //todo: add null exception
        System.out.println(getPatient(nric).toString());
    }

    //view all patients
    public void viewPatient() {
        System.out.println(patientList.toString());
    }

    @Override
    public String toString() {
        return "aaa";
    }
}
