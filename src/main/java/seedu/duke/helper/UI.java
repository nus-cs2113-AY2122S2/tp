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
        System.out.println("Here are the commands:");
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
}
