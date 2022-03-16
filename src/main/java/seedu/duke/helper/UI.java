package seedu.duke.helper;

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
        System.out.println("1. add patient /info. Example:");
        System.out.println("add patient /info S1234567A, John Doe, 23, M, 10 Baker Street," +
                " 1999-12-31, 2021-02-15");
        System.out.println("2. delete patient. Example:");
        System.out.println("delete patient /info 1");
        System.out.println("3. view patient. Example:");
        System.out.println("view patient");
        System.out.println("4. add doctor. Example:");
        System.out.println("add doctor /info S1234567A, Shirley Tan, 40, F, 1 Baker Road, " +
                "1980-12-31, Dermatology");
        System.out.println("5. delete doctor. Example:");
        System.out.println("delete doctor /info 1");
        System.out.println("6. view doctor. Example:");
        System.out.println("view doctor");
        System.out.println("7. add medicine. Example:");
        System.out.println("add medicine /info 1, Paracetamol, 500, 2023-06-11, Slight headache, 10");
        System.out.println("8. delete medicine. Example:");
        System.out.println("delete medicine /info 1");
        System.out.println("9. view medicine. Example:");
        System.out.println("view medicine");
        System.out.println("10. bye. Example:");
        System.out.println("bye");
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

    public void printDeletePatientExampleMessage(PatientList patientList) {
        System.out.println("Please input a postive number up to " + patientList.getSize() + " only.");
        System.out.println("Here is an example:");
        System.out.println("delete patient /info 1");
    }

    public void printNullParametersMessage() {
        System.out.println("Parameters missing or not detected. Please use /info for parameters.");
    }
}
