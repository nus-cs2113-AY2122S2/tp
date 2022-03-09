package seedu.duke.assets;

import java.util.ArrayList;

public class PatientList {

    ArrayList<Patient> patientList;


    public Patient getPatient(String NRIC) {
       for (Patient patient : patientList) {
           if (patient.getNRIC().equals(NRIC)){
               return patient;
           }
       }
       return null;
    }

    //view particular patient
    public void viewPatient(String NRIC) {
        //todo: add null exception
        System.out.println(getPatient(NRIC).toString());
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
