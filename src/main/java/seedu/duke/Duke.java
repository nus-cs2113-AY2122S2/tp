package seedu.duke;

import java.sql.Time;
import java.util.Scanner;

import static seedu.duke.ClearCommand.clearList;
import static seedu.duke.DeleteCommand.executeDelete;
import static seedu.duke.Parser.getDeleteString;

public class Duke {

    private static Scanner SCANNER = new Scanner(System.in);
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String CLEAR_COMMAND = "clear";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        System.out.println("Hello " + SCANNER.nextLine());

        //check for bye message
        while (true) {
            String input = getInput();
            Parser parser = new Parser(input);
            String parsedCommand = parser.getCommand();
            if (parsedCommand.equals(ADD_COMMAND)) {
                addEvent(parser);
            } else if (parsedCommand.equals(LIST_COMMAND)) {
                listTimetable();
            } else if (parsedCommand.equals(DELETE_COMMAND)) {
                executeDelete(getDeleteString(input));
            } else if (parsedCommand.equals(CLEAR_COMMAND)) {
                clearList();
            }

        }
    }

    private static String getInput() {
        String newInput = SCANNER.nextLine();
        String trimmedInput = newInput.trim();
        return trimmedInput;
    }

    private static void addEvent(Parser parser) {
        String[] eventDescription = parser.getAddDescription();
        Event upcomingEvent = AddCommand.execute(eventDescription);
        AddCommand.printConfirmation(upcomingEvent);
    }

    private static void listTimetable() {
        for (int i = 0; i < Timetable.size(); i++) {
            System.out.println(i + 1 + "." + Timetable.get(i));
        }
    }

}
