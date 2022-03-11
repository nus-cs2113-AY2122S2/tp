package seedu.duke;

import seedu.duke.commands.Command;

import java.util.Scanner;

import static seedu.duke.Parser.getDeleteString;

public class Duke {

    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        System.out.println("Hello " + SCANNER.nextLine());

        Timetable timetable = new Timetable();

        while (true) {
            String input = getInput();
            Parser parser = new Parser(input);
            Command parsedCommand = parser.parseCommand();
            parsedCommand.execute(timetable);
        }
    }

    private static String getInput() {
        String newInput = SCANNER.nextLine();
        String trimmedInput = newInput.trim();
        return trimmedInput;
    }


}
