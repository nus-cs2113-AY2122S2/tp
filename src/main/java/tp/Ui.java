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

    public void generateResponse(String message) {
        System.out.println(message);
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

    public void printAddHelp() {
        String addDoctor = "add doctor /id ID /n NAME /ph PHONE /e EMAIL" + System.lineSeparator();
        String addPatient = "add patient /id ID /n NAME /ph PHONE /e EMAIL /s SYMPTOM /d DESCRIPTIONS"
                                    + System.lineSeparator();
        String addAppointment = "add appointment /t 2022-03-19T15:16:00 /d DOCTOR_NO /p PATIENT_NO"
                                        + System.lineSeparator();
        System.out.println("1. To add something:" + System.lineSeparator()
                                   + addDoctor + addPatient + addAppointment);
    }

    public void printListHelp() {
        String listDoctor = "list doctor" + System.lineSeparator();
        String listPatient = "list patient" + System.lineSeparator();
        String sortAppointment = "sort appointment according to appointment time"
                                         + System.lineSeparator();
        String listAppointment = "list appointment" + System.lineSeparator();
        System.out.println("2. To list something:" + System.lineSeparator()
                                   + listDoctor + listPatient + sortAppointment + listAppointment);
    }

    public void printDeleteHelp() {
        String deleteDoctor = "delete doctor DOCTOR_NO" + System.lineSeparator();
        String deletePatient = "delete patient PATIENT_NO" + System.lineSeparator();
        String deleteAppointment = "delete appointment APPOINTMENT_NO." + System.lineSeparator();
        System.out.println("3. To delete something:" + System.lineSeparator()
                                   + deleteDoctor + deletePatient + deleteAppointment);
    }

    public void printSearchHelp() {
        String searchDoctor = "search doctor DOCTOR_NO" + System.lineSeparator();
        String searchPatient = "search patient PATIENT_NO" + System.lineSeparator();
        String searchAppointment = "search appointment DATETIME" + System.lineSeparator();
        System.out.println("4. To search for something:" + System.lineSeparator()
                                   + searchDoctor + searchPatient + searchAppointment);
    }

    public void printSortHelp() {
        String sortAppointment = "sort appointment" + System.lineSeparator();
        System.out.println("5. To sort something:" + System.lineSeparator() + sortAppointment);
    }

    public void printGetHelp() {
        String getAppointment = "get appointment DOCTOR_ID" + System.lineSeparator();
        System.out.println("6. To get appointments of a doctor: "
                                   + System.lineSeparator() + getAppointment);
    }

    public void printHelpPage() {
        System.out.print(boundary);
        System.out.println("Do not remember the command format? Here are some commands you can try:");

        printAddHelp();
        printListHelp();
        printDeleteHelp();
        printSearchHelp();
        printSortHelp();
        printGetHelp();

        System.out.println("Hope they are helpful to you~");
        System.out.print(boundary);
    }
}
