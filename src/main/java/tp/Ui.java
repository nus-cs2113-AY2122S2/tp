package tp;

import java.util.Scanner;

public class Ui {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();

    // Prints welcome message.
    public void sayHello() {
        System.out.print(boundary);
        System.out.println("Hello! This is IHospital.");
        System.out.print("What can I do for you?" + System.lineSeparator() + boundary);
    }

    // Prints goodbye message.
    public void sayGoodbye() {
        System.out.print(boundary + "Bye. Hope to see you again soon!" + System.lineSeparator() + boundary);
    }

    public void printDoctorPage(DoctorList doctors) {
        System.out.print(boundary);
        System.out.println("Welcome to Doctor Page.");
        System.out.print(doctors);
    }

    public void printPatientPage(PatientList patients) {
        System.out.print(boundary);
        System.out.println("Welcome to Patient Page.");
        System.out.print(patients);
    }
}
