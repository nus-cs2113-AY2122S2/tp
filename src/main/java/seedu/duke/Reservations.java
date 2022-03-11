package seedu.duke;

import java.util.ArrayList;

public class Reservations {
    private ArrayList<Reservation> reservations;

    public Reservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public int getSize() {
        return reservations.size();
    }

    public Reservation getReservation(int index) {
        return reservations.get(index);
    }

    public void addReservation(Reservation newReservation) {
        reservations.add(newReservation);
    }

    public void removeReservation(int index) {
        reservations.remove(index);
    }
}