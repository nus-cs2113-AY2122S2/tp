package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservation;
import seedu.duke.Reservations;

import java.util.Scanner;

public class ReservationCommand extends Command {
    public void execute(Packages packages, Reservations reservations) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please choose which function you would like to perform for Reservations.");
        System.out.println("1. Add Reservation");
        System.out.println("2. Remove Reservation");
        System.out.println("3. Check Reservations");
        int choice = Integer.parseInt(s.nextLine());
        switch (choice) {
        case 1:
            addReservation(reservations);
            return;
        case 2:
            deleteReservation(reservations);
            return;
        case 3:
            printReservation(reservations);
            return;
        default:
            System.out.println("Please only enter numbers 1-3!");
        }
    }

    public void printReservation(Reservations r) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Reservation ID to check details: ");
        int index = sc.nextInt();
        boolean reservationFound = false;
        for (int i = 0; i < r.getReservationSize(); i++) {
            if (r.getReservation(i).getReservationID() == index) {
                System.out.println(r.getReservation(i));
                reservationFound = true;
                break;
            }
        }
        if (!reservationFound) {
            System.out.println("Reservation ID could not be found. Please try again with a valid ID.");
        }
    }

    public void addReservation(Reservations r) {
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

        r.addReservation(new Reservation(rid, tid, name, number, pax));
    }

    public void deleteReservation(Reservations r) {
        Scanner p = new Scanner(System.in);
        System.out.println("Please enter the reservation ID to be deleted: ");
        int index = p.nextInt();
        boolean deletionFound = false;
        for (int i = 0; i < r.getReservationSize(); i++) {
            if (r.getReservation(i).getReservationID() == index) {
                System.out.println("Deleting Reservation");
                r.removeReservation(i);
                deletionFound = true;
                break;
            }
        }

        if (!deletionFound) {
            System.out.println("Reservation ID could not be found. Please try again with a valid ID.");
        }
    }
}
