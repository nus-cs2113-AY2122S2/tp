package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservation;
import seedu.duke.Reservations;

public class RemoveReservationCommand extends Command{
    private int travelPackageID;
    private String contact;

    public RemoveReservationCommand(int id,String contact){
        this.travelPackageID = id;
        this.contact = contact;
    }
    public void execute(Packages packages) {
        for (int i = 0; i < packages.getSize(); i++) {
            if (packages.getPackage(i).getID() == this.travelPackageID) {
                Reservations r = packages.getPackage(i).getReservationList();
                for (int j = 0; j < r.getReservationSize(); j++) {
                    if (this.contact.equals(r.getReservation(j).getContactNumber())){
                        r.removeReservation(j);
                        break;
                    }

                }

            }
        }
    }

}
