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
        return parser.commandParser(userInput)[0];
    }

    public String readParameters() {
        return parser.commandParser(userInput)[1];
    }
}
