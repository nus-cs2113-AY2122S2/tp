package seedu.duke.commands;

import seedu.duke.Parser;
import seedu.duke.Timetable;
import java.util.Scanner;

public abstract class Command {

    public static final String EXIT_COMMAND = "exit";

    public abstract String execute(Timetable timetable);

    public static void executeCommand(String userInput, Timetable timetable, Scanner in) {
        while (!userInput.equalsIgnoreCase(EXIT_COMMAND)) {
            String trimmedInput = userInput.trim();
            Parser parser = new Parser(trimmedInput);
            Command parsedCommand = parser.parseCommand();
            String feedback = parsedCommand.execute(timetable);
            System.out.println(feedback);
            userInput = in.nextLine();
        }
    }
}
