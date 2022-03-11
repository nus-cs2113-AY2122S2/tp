//package seedu.duke.command;
//import seedu.duke.Reservations;
//import seedu.duke.Reservation;
//
//
//public class DeleteReservationCommand extends ReservationCommand {
//    private int id;
//
//    public DeleteReservationCommand(int id) {
//        super(false);
//        this.id = id;
//    
//
//    public void execute(Reservations reservations) {
//        for (int i = 0; i < reservations.getSize(); i++) {
//            if (reservations.getReservation(i).getReservationID() == id) {
//                reservations.removeReservation(i);
//                break;
//            }
//        }
//    }
//}