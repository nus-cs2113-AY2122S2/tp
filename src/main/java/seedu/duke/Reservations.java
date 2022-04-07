package seedu.duke;

import java.util.ArrayList;

//arrayList of reservations
//handle adding, deletion of reservations. Used in TravelPackage class.
public class Reservations {
    private final ArrayList<Reservation> reservations;

    public Reservations() {
        this.reservations = new ArrayList<>();
    }

    public Reservations(ArrayList<Reservation> r) {
        this.reservations = r;
    }

    public Reservation getReservation(int index) {
        return reservations.get(index);
    }

    public int getReservationSize() {
        return reservations.size();
    }

    public void printAllReservations() {
        if (reservations.size() == 0) {
            System.out.println("No reservations added yet!");
        }
        else {
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println(i + 1 + ". " + reservations.get(i).toString());
            }
        }
    }

    public void initReservation(Reservation newReservation) {
        reservations.add(newReservation);
    }

    public void addReservation(Reservation newReservation) {
        if (sameContactExist(newReservation.getContactNumber())) {
            System.out.println("Reservation under this number already exists! Please try again.");
        } else {
            reservations.add(newReservation);
            System.out.println("RESERVATION ADDED");
        }
    }

    // remove reservation from list using index
    public void removeReservation(int index) {
        reservations.remove(index);
        System.out.println("RESERVATION DELETED");
    }

    private boolean sameContactExist(String contactNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getContactNumber().equals(contactNumber)) {
                return true;
            }
        }
        return false;
    }

}