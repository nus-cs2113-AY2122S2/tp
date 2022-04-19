package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservations;

public class RemoveReservationCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    private final int travelPackageID;
    private final String contact;

    public RemoveReservationCommand(int id, String contact) {
        this.travelPackageID = id;
        this.contact = contact;
    }

    public void execute(Packages packages) {
        boolean isFoundID = false;
        for (int i = 0; i < packages.getSize(); i++) {
            if (packages.getPackage(i).getID() == this.travelPackageID) {
                Reservations r = packages.getPackage(i).getReservationList();
                for (int j = 0; j < r.getReservationSize(); j++) {
                    if (this.contact.equals(r.getReservation(j).getContactNumber())) {
                        int result = packages.getPackage(i).getNumParticipants() - r.getReservation(j).getNumOfPax();
                        if ((0 <= result) && (result < packages.getPackage(i).getMaxParticipants())) {
                            packages.getPackage(i).removeParticipants(r.getReservation(j).getNumOfPax());
                            r.removeReservation(j);
                        } else {
                            System.out.println("Removing too many participants. Please try again.");
                        }
                        isFoundID = true;
                        break;
                    }
                }
            }
        }
        if (!isFoundID) {
            System.out.println("Travel Package ID or Mobile Phone not found. Please try again.");
        }
    }
}
