package seedu.duke.assets;

import java.util.ArrayList;


public class DoctorList {



    private ArrayList<Doctor> doctors = new ArrayList<>();

    public Doctor getDoctor(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public void add(String[] addDoctorParameters) {
        Doctor newDoctor = new Doctor(addDoctorParameters[0],addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]), addDoctorParameters[3].charAt(0),
                addDoctorParameters[4],addDoctorParameters[5], addDoctorParameters[6]);
        doctors.add(newDoctor);
    }

    //view particular doctor
    public void viewDoctor(String nric) {
        Doctor doctor = getDoctor(nric);
        if (doctor == null) {
            System.out.println("Doctor doesn't exist please try again!");
            return;
        }
        System.out.println(doctor);
    }

    //view all doctor
    public void viewDoctor() {
        System.out.println(this);
    }

    //get the number of doctors
    public int getSizeDoctor() {
        return doctors.size();
    }

    //remove the specific doctor
    public void removeDoctor(int index) {
        doctors.remove(index);
    }

    @Override
    public String toString() {
        if (getSizeDoctor() == 0) {
            return "There are no doctors.";
        }
        String doctorName = " ";
        int index = 1;
        for (Doctor doctor : this.doctors) {
            doctorName += String.format("%d. %s", index, doctor.toString());
            if (index != this.getSizeDoctor()) {
                doctorName += "\n";
            }
            index++;
        }
        return doctorName;
    }

    public ArrayList<Doctor> getList() {
        return doctors;
    }
}
