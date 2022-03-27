package seedu.duke;

import java.util.ArrayList;

public class Reservations {
    private ArrayList<Reservation> reservations;

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

    public void addReservation(Reservation newReservation) {
        reservations.add(newReservation);
        System.out.println("RESERVATION ADDED");

    }

    public void removeReservation(int index) {
        reservations.remove(index);
    }

}