package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Command;

public class TARBS {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Storage storage = new Storage("data.txt");
        Packages packages = storage.createPackages();

        boolean endProgram = false;
        System.out.println("Welcome to Travel Agency Booking Reservation System!");
        Scanner sc = new Scanner(System.in);
        while (!endProgram) {
            storage.savePackageToFile(packages);
            System.out.println();
            System.out.println("Please enter command: ");
            try {
                Command command = Parser.parse(sc.nextLine());
//              command.execute(packages, reservations);
                command.execute(packages);
                endProgram = command.getIsExit();
            } catch (Exception ex) {
                System.out.println("Wrong format. All available" +
                        " commands can be seen with the 'help' command");
            }
        }
        storage.savePackageToFile(packages);
    }

}
