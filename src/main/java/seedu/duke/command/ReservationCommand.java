package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservation;

//add reservation to TravelPackage using travelpackage id
public class ReservationCommand extends Command {

    public static final String COMMAND_WORD = "reserve";

    private final Reservation newReservation;

    public ReservationCommand(int travelPackageID, String name, String number, int pax) {
        this.newReservation = new Reservation(travelPackageID, name, number, pax);
    }

    public void execute(Packages packages) {
        boolean isFoundID = false;
        for (int i = 0; i < packages.getSize(); i++) {
            if (packages.getPackage(i).getID() == newReservation.getPackageID()) {
                // handle participant overflow
                if (newReservation.getNumOfPax() + packages.getPackage(i).getNumParticipants() > packages.getPackage(i)
                        .getMaxParticipants()) {
                    System.out.println(
                            "Too many participants, maximum amount of participants will be exceeded. Please try again.");
                } else {
                    packages.getPackage(i).getReservationList().addReservation(this.newReservation);
                    packages.getPackage(i).addParticipants(newReservation.getNumOfPax());
                }
                isFoundID = true;
                break;

            }
        }
        if (!isFoundID) {
            System.out.println("Travel Package ID not found. Please try again.");
        }

    }
}
