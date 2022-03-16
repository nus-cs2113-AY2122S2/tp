package seedu.duke;

import util.exceptions.NullException;

import java.util.Scanner;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Duke {
    public static void hello() {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        String logo = "Simplst";
        System.out.println("Hello from\n" + logo);
        System.out.println("What would you like to do?");
    }

    private static void bye() {
        System.out.println("Bye");
    }

    public static void main(String[] args) throws NullException {
        hello();
        UserInterface.run();
        bye();
    }
}
