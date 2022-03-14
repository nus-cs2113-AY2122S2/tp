package seedu.duke.commands;

import seedu.duke.Parser;
import seedu.duke.Timetable;
import java.util.Scanner;

import static seedu.duke.commands.ExitCommand.EXIT_COMMAND;

public abstract class Command {

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
