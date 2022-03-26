package seedu.duke.helper.finder;

import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;

public class PatientFinder {

    public static Patient findPatientByNric(PatientList patientLists, String requiredNric) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getNric().equals(requiredNric)) {
                System.out.println("Index of NRIC in list base 0 = " + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByName(PatientList patientLists, String requiredName) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getFullName().contains(requiredName)) {
                System.out.println("Index of name in list base 0 = " + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByAge(PatientList patientLists, int requiredAge) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getAge() == requiredAge) {
                System.out.println("Index of age in list base 0 = " + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByGender(PatientList patientLists, char requiredGender) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getGender() == requiredGender) {
                System.out.println("Index of Gender in list base 0 = " + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByAddress(PatientList patientLists, String requiredAddress) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getAddress().contains(requiredAddress)) {
                System.out.println("Index of Address in list base 0 = " + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByDob(PatientList patientLists, String requiredDob) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getDob().contains(requiredDob)) {
                System.out.println("Index of DOB in list base 0 = " + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByDateAdmission(PatientList patientLists, String requiredDob) {
        for (Patient patient : patientLists.getList()) {
            if (patient.getDateAdmission().contains(requiredDob)) {
                System.out.println("Index of Date Admission in list base 0 = "
                        + patientLists.getList().indexOf(patient));
                return patient;
            }
        }
        return null;
    }

}


