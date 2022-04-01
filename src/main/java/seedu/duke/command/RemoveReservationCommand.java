package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservation;
import seedu.duke.Reservations;

public class RemoveReservationCommand extends Command{
    private final int travelPackageID;
    private final String contact;

    public RemoveReservationCommand(int id,String contact){
        this.travelPackageID = id;
        this.contact = contact;
    }
    public void execute(Packages packages) {
        boolean foundID = false;
        for (int i = 0; i < packages.getSize(); i++) {
            if (packages.getPackage(i).getID() == this.travelPackageID) {
                Reservations r = packages.getPackage(i).getReservationList();
                for (int j = 0; j < r.getReservationSize(); j++) {
                    if (this.contact.equals(r.getReservation(j).getContactNumber())){
                        //check that numParticipants do not go below 0.
                        int result = packages.getPackage(i).getNumParticipants() - r.getReservation(j).getNumOfPax();
                        if (( 0 <= result) && (result < packages.getPackage(i).getMaxParticipants())) {
                            packages.getPackage(i).removeParticipants(r.getReservation(j).getNumOfPax());
                            r.removeReservation(j);

                        }
                        else {
                            System.out.println("Removing too many participants. Please try again.");
                        }
                        foundID = true;
                        break;
                    }


                }

            }
        }
        if (!foundID) {
            System.out.println("Travel Package ID or Mobile Phone not found. Please try again.");
        }


    }

}
