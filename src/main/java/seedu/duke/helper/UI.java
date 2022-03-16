package seedu.duke.helper;

import seedu.duke.assets.DoctorList;
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
        System.out.println("Welcome! This is the Hospital Management System.");
        System.out.println("Please type in your input:");
    }

    public void printHelp() {
        printNewLineSeparator();
        System.out.println("Here are the commands and examples:");
        System.out.println("1. add patient\n2. delete patient\n3. view patient");
        System.out.println("4. add doctor\n5. delete doctor\n6. view doctor");
        System.out.println("6. add patient\n7. delete patient\n9. view patient");
        System.out.println("10. bye");
    }

    public void printWrongInput() {
        printNewLineSeparator();
        System.out.println("Sorry. Input was invalid.");
        printHelp();
    }

    public void printBye() {
        printNewLineSeparator();
        System.out.println("Goodbye! Exiting the programme.");
    }

    public void printAddPatientExampleMessage() {
        System.out.println("Please note the error(s) mentioned above and try again!");
        System.out.println("Here are two examples. Please follow the input order:");
        System.out.println("add patient /info S1234567A, John Doe, 23, M, 10 Baker Street,"
                + " 1999-12-31, 2021-02-15");
        System.out.println("add patient /info T4867591Z, Mary Douglas Owen, 25, F, 15 King's Avenue,"
                + "1997-08-26, 2020-03-30");
    }

    public void printAddDoctorExampleMessage() {
        System.out.println("Please note the error(s) mentioned above and try again!");
        System.out.println("Here are two examples. Please follow the input order:");
        System.out.println("add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street,"
                + " 1999-12-31, Urinology");
        System.out.println("add doctor /info T4867591Z, Mary Douglas Owen, 25, F, 15 King's Avenue,"
                + "1997-08-26, Optometry");
    }

    public void printDeletePatientExampleMessage(PatientList patientList) {
        System.out.println("Please input a postive number up to " + patientList.getSize() + " only.");
        System.out.println("Here is an example:");
        System.out.println("delete patient /info 1");
    }

    public void printDeleteDoctorErrorMessage(DoctorList doctorList) {
        System.out.println("Please enter a number from 1 till  " + doctorList.getSizeDoctor() + " .");
        System.out.println("Here is an example:");
        System.out.println("delete doctor /info 1");
    }

    public void printNullParametersMessage() {
        System.out.println("Parameters missing or not detected. Please use /info for parameters.");
    }

    public void printPrompt() {
        System.out.print("You: ");
    }

    public void printParagraph(String paragraph) {
        System.out.print("HalpMi: ");
        String[] arrayOfSentences = paragraph.split("\n");
        for (String s : arrayOfSentences) {
            System.out.print("        ");
            System.out.println(s);
        }
    }

    public void printCont(String sentence) {
        System.out.print("        ");
        System.out.println(sentence);
    }
}
