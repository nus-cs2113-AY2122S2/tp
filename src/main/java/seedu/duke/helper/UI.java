package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;

import java.util.Scanner;

public class UI {
    private String userInput;
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

    public void printNewLineSeparator() {
        System.out.println("-------------------------");
    }

    public void printGreeting() {
        printNewLineSeparator();
        printParagraph("Welcome! This is the Hospital Management System.\n"
                + "Please type in your input");
    }

    public void printHelp() {
        printNewLineSeparator();
        printParagraph("Here are the commands and examples:\n"
                + "1. add patient\n"
                + "Example: add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15\n"
                + "2. delete patient\n"
                + "Example: delete patient /info S1234567J\n"
                + "3. view patient\nExample: view patient [/info NRIC]\n"
                + "4. add doctor\n"
                + "Example: add doctor /info S1234567A, Shirley Tan, 40, F, 1 Baker Road, 1980-12-31, Dermatology\n"
                + "5. delete doctor\nExample: delete doctor /info S1234567J\n"
                + "6. view doctor\nExample: view doctor [/info NRIC]\n"
                + "7. add medicine\nExample: add medicine /info Paracetamol, 500, 2023-06-11, Slight headache, 10\n"
                + "8. delete medicine\nExample: delete medicine /info 1\n"
                + "9. view medicine\nExample: view medicine [/info id]\n"
                + "10. bye\nExample: bye");
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
        printParagraph("Please input a positive number up to " + doctorList.getSizeDoctor() + " only.\n"
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
                + "add medicine /info Paracetamol,500,2023-12-12,Headaches,10");
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
}
