package seedu.duke;

import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static final String ADD_COMMAND = "add";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        System.out.println("Hello " + SCANNER.nextLine());

        String input = getInput();
        Parser parser = new Parser(input);
        if (parser.getCommand().equals(ADD_COMMAND)) {
            System.out.println(addEvent(parser));
        }
    }

    private static String getInput() {
        return SCANNER.nextLine().trim();
    }

    private static Event addEvent(Parser parser) {
        return AddCommand.execute(parser.getAddDescription());
    }
}
