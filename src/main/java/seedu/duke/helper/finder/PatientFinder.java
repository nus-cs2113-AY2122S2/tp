package seedu.duke.helper.finder;

import seedu.duke.assets.Patient;

import java.util.ArrayList;

public class PatientFinder {

    public static ArrayList<Patient> findPatientByNric(ArrayList<Patient> patients, String requiredNric) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getNric().equals(requiredNric)) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    public static ArrayList<Patient> findPatientByName(ArrayList<Patient> patients, String requiredName) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getFullName().contains(requiredName)) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    public static ArrayList<Patient> findPatientByAge(ArrayList<Patient> patients, int requiredAge) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getAge() == requiredAge) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    public static ArrayList<Patient> findPatientByGender(ArrayList<Patient> patients, char requiredGender) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getGender() == requiredGender) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    public static ArrayList<Patient> findPatientByAddress(ArrayList<Patient> patients, String requiredAddress) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getAddress().contains(requiredAddress)) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    public static ArrayList<Patient> findPatientByDob(ArrayList<Patient> patients, String requiredDob) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getDob().contains(requiredDob)) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    public static ArrayList<Patient> findPatientByDateAdmission(ArrayList<Patient> patients, String requiredDob) {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getDateAdmission().contains(requiredDob)) {
                patientArrayList.add(patient);
            }
        }
        return getPatients(patientArrayList);
    }

    private static ArrayList<Patient> getPatients(ArrayList<Patient> patientArrayList) {
        if (patientArrayList.isEmpty()) {
            return null;
        } else {
            return patientArrayList;
        }
    }

}


