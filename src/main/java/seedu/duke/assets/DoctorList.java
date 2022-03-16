package seedu.duke.assets;

import java.util.ArrayList;

public class DoctorList {

    ArrayList<Doctor> doctors = new ArrayList<>();

    public void add(String[] addDoctorParameters) {
        Doctor newDoctor = new Doctor(addDoctorParameters[0],addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]), addDoctorParameters[3].charAt(0),
                addDoctorParameters[4],addDoctorParameters[5], addDoctorParameters[6]);
        doctors.add(newDoctor);
    }

    //view particular doctor
    public void viewDoctor(String nric) {

    }

    //view all doctor
    public void viewDoctor() {

    }
}
