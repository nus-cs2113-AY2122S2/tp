package seedu.duke.helper;

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
        System.out.println("Here are two examples. Please follow the input order.");
        System.out.println("add patient /info S1234567A, John Doe, 23, M, 10 Baker Street,"
                + " 1999-12-31, 2021-02-15");
        System.out.println("add patient /info T4867591Z, Mary Douglas Owen, 25, F, 15 King's Avenue,"
                + "1997-08-26, 2020-03-30");
    }

    public void printAddPatientNullParametersMessage() {
        System.out.println("Parameters missing or no detected. Please use /info for parameters.");
    }
}
