package seedu.duke;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import seedu.duke.command.Command;

public class Duke {
    private Packages packages;

    // public TravelPackage(String name, int id, Date startDate, Date endDate,
    // String hotel, double price, String country, int maxParticipants) {
    public Duke() {
        ArrayList<TravelPackage> temp = new ArrayList<>();
        temp.add(new TravelPackage("Experience Korea!", 1, new Date(25032021), new Date(28032021), "Four Seasons",
                500.25, "Korea", 20));
        packages = new Packages(temp);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {

        boolean endProgram = false;
        System.out.print("Welcome to Travel Agency Booking Reservation System!");

        while (!endProgram) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter command: ");
            Command command = Parser.parse(sc.nextLine());
            command.execute(packages);
            endProgram = command.getIsExit();
        }
    }

}
