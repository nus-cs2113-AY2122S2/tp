package seedu.duke.assets;

import java.util.ArrayList;


public class DoctorList {

    private boolean isNull(String string) {
        return string == null;
    }

    private ArrayList<Doctor> doctors = new ArrayList<>();

    public Doctor getDoctor(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }


    //view particular doctor
    public void viewDoctor(String nric) {
        if (isNull(nric)) {
            return;
        }
        System.out.println(getDoctor(nric).toString());
    }

    //view all doctor
    public void viewDoctor() {
        System.out.println(doctors.toString());
    }

    //get the number of doctors
    public int getSize() {
        return doctors.size();
    }

    //remove the specific doctor
    public void removeDoctor(int index) {
        doctors.remove(index);
    }

    @Override
    public String toString() {
        return "aaa";
    }
}
