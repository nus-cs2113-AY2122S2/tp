package seedu.duke;

import java.util.ArrayList;

//arrayList of reservations
//handle adding, deletion of reservations. Used in TravelPackage class.
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

    public void printAllReservations(){
        for (int i = 0; i<reservations.size();i++){
            System.out.println(i+ ". " + reservations.get(i).toString());
        }
    }

    public void addReservation(Reservation newReservation) {
        reservations.add(newReservation);
        System.out.println("RESERVATION ADDED");
    }

    //remove reservation from list using index
    public void removeReservation(int index) {
        reservations.remove(index);
        System.out.println("RESERVATION DELETED");
    }

}