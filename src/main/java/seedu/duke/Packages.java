package seedu.duke;

import java.util.ArrayList;

public class Packages {
    private ArrayList<TravelPackage> packages;

    public Packages(ArrayList<TravelPackage> packages) {
        this.packages = packages;
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
}