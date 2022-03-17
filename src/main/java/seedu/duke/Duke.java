package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.command.Command;

public class Duke {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Packages packages = new Packages();
        boolean endProgram = false;
        System.out.println("Welcome to Travel Agency Booking Reservation System!");
        Scanner sc = new Scanner(System.in);
        while (!endProgram) {
            System.out.println("Please enter command: ");
            Command command = Parser.parse(sc.nextLine());
            command.execute(packages);
            endProgram = command.getIsExit();
        }
    }

}
