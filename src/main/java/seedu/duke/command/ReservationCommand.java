package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservation;
import java.util.Scanner;

public class ReservationCommand extends Command {
    public void execute(Packages packages) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please choose which function you would like to perform for Reservations.");
        System.out.println("1. Add Reservation");
        System.out.println("2. Remove Reservation");
        System.out.println("3. Check Reservations");
        int choice = Integer.parseInt(s.nextLine());
        switch (choice) {
        case 1:
            addReservation(packages);
            return;
        case 2:
            deleteReservation(packages);
            return;
        case 3:
            printReservation(packages);
            return;
        default:
            System.out.println("Please only enter numbers 1-3!");
        }
    }

    public void printReservation(Packages packages) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Reservation ID to check details: ");
        int index = sc.nextInt();
        boolean reservationFound = false;
        for (int i = 0; i < packages.getReservationSize(); i++) {
            if (packages.getReservation(i).getReservationID() == index) {
                System.out.println(packages.getReservation(i));
                reservationFound = true;
                break;
            }
        }
        if (!reservationFound) {
            System.out.println("Reservation ID could not be found. Please try again with a valid ID.");
        }
    }

    public void addReservation(Packages packages) {
        Scanner c = new Scanner(System.in);
        System.out.println("Enter reservation ID: ");
        final int rid = Integer.parseInt(c.nextLine());
        System.out.println("Enter customer name: ");
        final String name = c.nextLine();
        System.out.println("Enter mobile number: ");
        final String number = c.nextLine();
        System.out.println("Enter Travel Package ID: ");
        final String tid = c.nextLine();
        System.out.println("Enter number of pax: ");
        final int pax = Integer.parseInt(c.nextLine());

        packages.addReservation(new Reservation(rid, tid, name, number, pax));
    }

    public void deleteReservation(Packages packages) {
        Scanner p = new Scanner(System.in);
        System.out.println("Please enter the reservation ID to be deleted: ");
        int index = p.nextInt();
        boolean deletionFound = false;
        for (int i = 0; i < packages.getReservationSize(); i++) {
            if (packages.getReservation(i).getReservationID() == index) {
                System.out.println("Deleting Reservation");
                packages.removeReservation(i);
                deletionFound = true;
                break;
            }
        }

        if (!deletionFound) {
            System.out.println("Reservation ID could not be found. Please try again with a valid ID.");
        }
    }
}
