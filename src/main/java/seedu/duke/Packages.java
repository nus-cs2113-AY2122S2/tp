package seedu.duke;

import java.util.ArrayList;
//Packages include arrayList packages to hold all TravelPackages

public class Packages {
    private ArrayList<TravelPackage> packages;
    private ArrayList<Reservation> reservations;

    public Packages() {
        this.packages = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public Packages(ArrayList<TravelPackage> t, ArrayList<Reservation> r){
        this.packages = t;
        this.reservations = r;
    }

    public int getSize() {
        return packages.size();
    }

    public TravelPackage getPackage(int index) {
        return packages.get(index);
    }

    public void addPackage(TravelPackage newPackage) {
        packages.add(newPackage);
    }

    public void removePackage(int index) {
        packages.remove(index);
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