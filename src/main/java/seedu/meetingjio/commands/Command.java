package seedu.meetingjio.commands;

import seedu.meetingjio.Parser;
import seedu.meetingjio.timetables.MasterTimetable;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.meetingjio.commands.ExitCommand.EXIT_COMMAND;
/**
 * Represents an executable command.
 */

public abstract class Command {

    public abstract String execute(MasterTimetable masterTimetable);

    public static Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Executes the command until termination or the exit command is called.
     *
     * @param userInput full user input
     * @param masterTimetable MasterTimetable 
     * @param in next user input
     */
    public static void executeCommand(String userInput, MasterTimetable masterTimetable, Scanner in) {
        while (!userInput.equalsIgnoreCase(EXIT_COMMAND)) {
            String trimmedInput = userInput.trim();
            Parser parser = new Parser(trimmedInput);
            Command parsedCommand = parser.parseCommand();
            try {
                String feedback = parsedCommand.execute(masterTimetable);
                System.out.println(feedback);
            } catch (AssertionError ae) {
                logger.log(Level.INFO, "Assertion Error");
            }
            userInput = in.nextLine();
        }
    }
}
