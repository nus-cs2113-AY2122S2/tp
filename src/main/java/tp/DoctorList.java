package tp;

import tp.person.Doctor;
import tp.person.Person;

import java.util.ArrayList;

public class DoctorList {
    public static String boundary = "____________________________________________________________" + System.lineSeparator();
    protected ArrayList<Doctor> doctors = new ArrayList<>();
    protected int countDoctor;

    public DoctorList() {
        countDoctor = 0;
    }

    public Person getDoctor(int index) {
        return doctors.get(index);
    }

    /**
     * Adds a doctor to the doctor list.
     *
     * @param name Name of doctor.
     * @param phoneNumber Phone number of the doctor.
     * @param email Email address of the doctor.
     */
    public void addDoctor(String name, String phoneNumber, String email) {
        doctors.add(new Doctor(countDoctor + 1, name, phoneNumber, email));
        countDoctor++;
        System.out.println(boundary + "Noted. I've added this doctor:");
        System.out.println(doctors.get(countDoctor - 1));
        System.out.print("Now you have " + countDoctor
                                 + " doctors recorded in the system." + System.lineSeparator() + boundary);
    }

    /**
     * Prints the current list of doctors.
     */
    public void printDoctorList() {
        System.out.println(boundary + "Here are the doctors in this hospital:");
        for (int i = 0; i < countDoctor; i++) {
            System.out.println((i + 1) + ". " + getDoctor(i));
        }
        System.out.print("You have " + countDoctor + " doctors recorded in the system."
                                 + System.lineSeparator() + boundary);
    }

    /**
     * Delete a doctor from doctor list.
     *
     * @param index Index of the doctor to be removed.
     */
    public void deleteDoctor(int index) {
        System.out.println(boundary + "Noted. I've removed this doctor:");
        System.out.println(doctors.get(index));
        System.out.print("Now you have " + (countDoctor - 1)
                                 + " doctors in the system." + System.lineSeparator() + boundary);
        doctors.remove(index);
        countDoctor -= 1;
    }
}
