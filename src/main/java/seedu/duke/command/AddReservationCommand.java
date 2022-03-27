//package seedu.duke.command;
//
//import seedu.duke.Reservations;
//import seedu.duke.Reservation;
//
//
//public class AddReservationCommand extends ReservationCommand {
//    private Reservation newReservation;
//
//    public AddReservationCommand(int reservationID, int packageID, String customerName, String contactNumber, int numOfPax) {
//        super(false);
//        this.newReservation = new Reservation(reservationID, packageID, customerName, contactNumber, numOfPax);
//    }
//
//    public void execute(Reservations reservations) {
//        reservations.addReservation(newReservation);
//    }
//
//}