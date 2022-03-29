package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservation;


import java.util.Scanner;
//add reservation to TravelPackage using travelpackage id
public class ReservationCommand extends Command {
    private Reservation newReservation;

    public ReservationCommand(int travelPackageID, String name, String number, int pax) {
        this.newReservation = new Reservation(travelPackageID, name, number, pax);
    }
    public void execute(Packages packages) {
            for (int i =0;i<packages.getSize();i++){
                if (packages.getPackage(i).getID() == newReservation.getPackageID()) {
                    packages.getPackage(i).getReservationList().addReservation(this.newReservation);
                }
            }
        }
    }

