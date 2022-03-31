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

    public void printHelpPage() {
        System.out.print(boundary);
        System.out.println("Do not remember the command format? Here are some commands you can try:");

        String addDoctor = "add doctor /id ID /n NAME /ph PHONE /e EMAIL" + System.lineSeparator();
        String addPatient = "add patient /id ID /n NAME /ph PHONE /e EMAIL" + System.lineSeparator();
        String addAppointment = "add appointment /t 2022-03-19T15:16:00 /d DOCTOR_ID /p PATIENT_ID"
                                        + System.lineSeparator();
        System.out.println("1. To add something:" + System.lineSeparator()
                                   + addDoctor + addPatient + addAppointment);

        String listDoctor = "list doctor" + System.lineSeparator();
        String listPatient = "list patient" + System.lineSeparator();
        String listAppointment = "list appointment" + System.lineSeparator();
        System.out.println("2. To list something:" + System.lineSeparator()
                                   + listDoctor + listPatient + listAppointment);

        String deleteDoctor = "delete doctor DOCTOR_ID" + System.lineSeparator();
        String deletePatient = "delete patient PATIENT_ID" + System.lineSeparator();
        String deleteAppointment = "delete appointment APPOINTMENT_NO." + System.lineSeparator();
        System.out.println("3. To delete something:" + System.lineSeparator()
                                   + deleteDoctor + deletePatient + deleteAppointment);

        System.out.println("Hope they are helpful to you~");
        System.out.print(boundary);
    }
}
