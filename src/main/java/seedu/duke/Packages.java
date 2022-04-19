package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
//Packages include arrayList packages to hold all TravelPackages

public class Packages {
    private final ArrayList<TravelPackage> packages;

    public Packages() {
        this.packages = new ArrayList<>();
    }

    public Packages(ArrayList<TravelPackage> t) {
        this.packages = t;
    }

    public int getSize() {
        return packages.size();
    }

    public TravelPackage getPackage(int index) {
        return packages.get(index);
    }

    public TravelPackage getPackageByID(int id) {
        for (TravelPackage travelPackage : packages) {
            if (travelPackage.getID() == id) {
                return travelPackage;
            }
        }
        return null;
    }

    public void addPackage(TravelPackage newPackage) {
        packages.add(newPackage);
    }

    public void removePackage(int index) {
        packages.remove(index);
    }

    // check if packageID already exists. return true if already exists - unique IDs
    // only!
    public boolean idExists(int id) {
        for (TravelPackage aPackage : packages) {
            if (aPackage.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainSamePackage(TravelPackage newPackage) {
        for (TravelPackage aPackage : packages) {
            if (aPackage.getName().equals(newPackage.getName()) &&
                    aPackage.getStartDate().equals(newPackage.getStartDate()) &&
                    aPackage.getEndDate().equals(newPackage.getEndDate()) &&
                    aPackage.getHotel().equals(newPackage.getHotel()) &&
                    aPackage.getPrice() == newPackage.getPrice() &&
                    aPackage.getCountry().equals(newPackage.getCountry()) &&
                    aPackage.getMaxParticipants() == newPackage.getMaxParticipants()) {
                return true;
            }
        }
        return false;
    }

}