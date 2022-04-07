package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.Patient;
import seedu.duke.assets.PatientList;
import seedu.duke.status.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private String userInput;
    private ArrayList<Patient> patients = new ArrayList<>();
    private Scanner reader = new Scanner(System.in);
    private Parser parser = new Parser();

    private void readInput() {
        this.userInput = reader.nextLine();
    }

    public String readCommand() {
        readInput();
        return parser.commandParser(userInput)[0].trim();
    }


    public String readParameters() {
        String[] userInputArray = parser.commandParser(userInput);
        if (userInputArray.length == 1) {
            return null;
        }
        return parser.commandParser(userInput)[1].trim();
    }

    public static void printNewLineSeparator() {
        System.out.println("-------------------------");
    }

    public void printGreeting() {
        printNewLineSeparator();
        printParagraph("Welcome! This is the Hospital Management System.\n"
                + "Please type in your input");
    }

    public void printLogo() {
        String logo = "------------------------------\n"
                + "HALPMI";

        System.out.println(logo);
    }

    public static void printHelp() {
        printAddDoctorMessage();
        printNewLineSeparator();
    }

    public static void printAddDoctorMessage() {
        printNewLineSeparator();
        System.out.println("Feature: ADD DOCTOR");
        System.out.print("Format: add doctor /info [nric],[name],[age],[gender],");
        System.out.println("[address],[DOB],[Specialisation]");
        System.out.print("Example: add doctor /info S1234567A, John Doe,");
        System.out.println("23, M, 10 Baker Street, 1999-12-31, Urinology");
    }

    public int getSize() {
        return patients.size();
    }

    public void printWrongInput() {
        printNewLineSeparator();
        printParagraph("Sorry. Input was invalid.");
        printHelp();
    }

    public void printBye() {
        printNewLineSeparator();
        printParagraph("Goodbye! Exiting the programme.");
    }

    public void printAddPatientExampleMessage() {
        printParagraph("Please note the error(s) mentioned above and try again!\n"
                + "Here are two examples. Please follow the input order:\n"
                + "add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15\n"
                + "add patient /info T4867591Z, Mary Douglas Owen, 25, F, 15 King's Avenue, 1997-08-26, 2020-03-30");
    }

    public void printAddDoctorExampleMessage() {
        printParagraph("Please note the error(s) mentioned above and try again!\n"
                + "Here are two examples. Please follow the input order:\n"
                + "add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Urinology\n"
                + "add doctor /info T4867591Z, Mary Douglas Owen, 25, F, 15 King's Avenue, 1997-08-26, Optometry");
    }

    public void printDeletePatientExampleMessage(PatientList patientList) {
        printParagraph("Please input a positive number up to " + patientList.getSize() + " only.\n"
                + "Here is an example:\ndelete patient /info 1");
    }

    public void printDeleteDoctorErrorMessage(DoctorList doctorList) {
        printParagraph("Please input a positive number up to " + doctorList.getSize() + " only.\n"
                + "Here is an example:\ndelete doctor /info 1");
    }

    public void printAddAppointmentExampleMessage() {
        printParagraph("Please note the error(s) mentioned above and try again!\n"
                + "Here is an example. Please follow the input order:\n"
                + "add appointment /info S1234567A, Thomas, S1234567A, Ben, 2023-01-01, Knee checkup");
    }

    public void printNullParametersMessage() {
        UI.printParagraph("Parameters missing or not detected. Please use /info for parameters.");
    }

    public void printAddMedicineExampleMessage() {
        printParagraph("Invalid format. Please follow the below example and try again.\n"
                + "add medicine /info s231, Paracetamol,500,2023-12-12,Headaches,10");
    }

    public void printEditMedicineExampleMessage() {
        printParagraph("Invalid format. Please follow the below example and try again.\n"
                + "edit medicine /info s231, Paracetamol,500,2023-12-12,Headaches,10");
    }

    public void printFindDoctorExampleMessage() {
        printParagraph("find doctor /info name, Bruce Lee\n"
                + "find doctor /info nric, S1234567X\n"
                + "find doctor /info age, 23\n"
                + "find doctor /info gender, M\n"
                + "find doctor /info address, 15 King's Avenue\n"
                + "find doctor /info dob, 1999-12-31\n"
                + "find doctor /info specialization, Dermatology\n");
    }

    public void printFindPatientExampleMessage() {
        printParagraph("find patient /info name, Bruce Lee\n"
                + "find patient /info nric, S1234567X\n"
                + "find patient /info age, 23\n"
                + "find patient /info gender, M\n"
                + "find patient /info address, 15 King's Avenue\n"
                + "find patient /info dob, 1999-12-31\n"
                + "find patient /info admissiondate, 2021-02-15\n");
    }

    public void printFindMedicinetExampleMessage() {
        printParagraph("find medicine /info id, S123\n"
                + "find medicine /info name, Paracetamol\n"
                + "find medicine /info dosage, 500\n"
                + "find medicine /info expiry, 2023-06-11\n"
                + "find medicine /info sideeffects, headache\n"
                + "find medicine /info quantity, 10\n");
    }

    public static void printPrompt() {
        System.out.print("You: ");
    }

    public static void printParagraph(String paragraph) {
        String[] arrayOfSentences = paragraph.split("\n");
        System.out.println("HalpMi: " + arrayOfSentences[0]);
        for (int i = 1; i < arrayOfSentences.length; i++) {
            System.out.print("        ");
            System.out.println(arrayOfSentences[i]);
        }
    }

    public static void printCont(String sentence) {
        System.out.print("        ");
        System.out.println(sentence);
    }

    public void printDeleteMedicineExampleMessage(MedicineList medicineList) {
        printParagraph("Please input a positive number up to " + medicineList.getSize() + " only.\n"
                + "Here is an example:\ndelete patient /info 1");
    }

    public void print(Status status) {
        switch (status) {
        case ADD_PATIENT_SUCCESS:
            printParagraph("Patient has been added successfully!");
            break;
        case ADD_DOCTOR_SUCCESS:
            printParagraph("Doctor has been added successfully!");
            break;
        case ADD_MEDICINE_SUCCESS:
            printParagraph("Medicine has been added successfully!");
            break;
        case ADD_APPOINTMENT_SUCCESS:
            printParagraph("Appointment has been added successfully!");
            break;
        case DELETE_PATIENT_SUCCESS:
            printParagraph("Patient has been deleted successfully!");
            break;
        case DELETE_DOCTOR_SUCCESS:
            printParagraph("Doctor has been deleted successfully!");
            break;
        case DELETE_MEDICINE_SUCCESS:
            printParagraph("Medicine has been deleted successfully!");
            break;
        case DELETE_APPOINTMENT_SUCCESS:
            printParagraph("Appointment has been deleted successfully!");
            break;
        case EDIT_DOCTOR_SUCCESS:
            printParagraph("Doctor has been edited successfully!");
            break;
        case EDIT_PATIENT_SUCCESS:
            printParagraph("Patient has been edited successfully!");
            break;
        case EDIT_MEDICINE_SUCCESS:
            printParagraph("Medicine has been edited successfully!");
            break;
        case EDIT_APPOINTMENT_SUCCESS:
            printParagraph("Appointment has been edited successfully!");
            break;
        case PRINT_HELP:
            UI.printHelp();
            break;
        default:
            break;
        }
    }
}
