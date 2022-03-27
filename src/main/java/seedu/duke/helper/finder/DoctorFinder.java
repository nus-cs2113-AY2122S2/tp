package seedu.duke.helper.finder;

import seedu.duke.assets.Doctor;

import java.util.ArrayList;

public class DoctorFinder {

    public static ArrayList<Doctor> findDoctorByNric(ArrayList<Doctor> doctors, String requiredNric) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(requiredNric)) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    public static ArrayList<Doctor> findDoctorByName(ArrayList<Doctor> doctors, String requiredName) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getFullName().contains(requiredName)) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    public static ArrayList<Doctor> findDoctorByAge(ArrayList<Doctor> doctors, int requiredAge) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getAge() == requiredAge) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    public static ArrayList<Doctor> findDoctorByGender(ArrayList<Doctor> doctors, char requiredGender) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getGender() == requiredGender) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    public static ArrayList<Doctor> findDoctorByAddress(ArrayList<Doctor> doctors, String requiredAddress) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getAddress().contains(requiredAddress)) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    public static ArrayList<Doctor> findDoctorByDob(ArrayList<Doctor> doctors, String requiredDob) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getDob().contains(requiredDob)) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    public static ArrayList<Doctor> findDoctorBySpecialization(ArrayList<Doctor> doctors,
                                                               String requiredSpecialization) {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().contains(requiredSpecialization)) {
                doctorArrayList.add(doctor);
            }
        }
        return getDoctors(doctorArrayList);
    }

    private static ArrayList<Doctor> getDoctors(ArrayList<Doctor> doctorArrayList) {
        if (doctorArrayList.isEmpty()) {
            return null;
        } else {
            return doctorArrayList;
        }
    }

}


