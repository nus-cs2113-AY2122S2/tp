package seedu.duke.helper.finder;

import seedu.duke.assets.Doctor;
import seedu.duke.assets.DoctorList;

public class DoctorFinder {

    public static Doctor findDoctorByNric(DoctorList doctorLists, String requiredNric) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getNric().equals(requiredNric)) {
                System.out.println("Index of NRIC in list base 0 = " + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

    public static Doctor findDoctorByName(DoctorList doctorLists, String requiredName) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getFullName().contains(requiredName)) {
                System.out.println("Index of name in list base 0 = " + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

    public static Doctor findDoctorByAge(DoctorList doctorLists, int requiredAge) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getAge() == requiredAge) {
                System.out.println("Index of age in list base 0 = " + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

    public static Doctor findDoctorByGender(DoctorList doctorLists, char requiredGender) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getGender() == requiredGender) {
                System.out.println("Index of Gender in list base 0 = " + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

    public static Doctor findDoctorByAddress(DoctorList doctorLists, String requiredAddress) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getAddress().contains(requiredAddress)) {
                System.out.println("Index of Address in list base 0 = " + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

    public static Doctor findDoctorByDob(DoctorList doctorLists, String requiredDob) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getDob().contains(requiredDob)) {
                System.out.println("Index of DOB in list base 0 = " + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

    public static Doctor findDoctorBySpecialization(DoctorList doctorLists, String requiredSpecialization) {
        for (Doctor doctor : doctorLists.getList()) {
            if (doctor.getSpecialization().contains(requiredSpecialization)) {
                System.out.println("Index of Specialization in list base 0 = "
                        + doctorLists.getList().indexOf(doctor));
                return doctor;
            }
        }
        return null;
    }

}
