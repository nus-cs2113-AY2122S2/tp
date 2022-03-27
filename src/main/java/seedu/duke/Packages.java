package seedu.duke;

import java.util.ArrayList;
//Packages include arrayList packages to hold all TravelPackages

public class Packages {
    private ArrayList<TravelPackage> packages;

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

    public void addPackage(TravelPackage newPackage) {
        packages.add(newPackage);
    }

    public void removePackage(int index) {
        packages.remove(index);
    }

}