package seedu.duke.command;

import seedu.duke.Packages;

public class InfoCommand extends Command{

    private int travelPackageID;

    public InfoCommand (int id){
        this.travelPackageID = id;
    }

    public void execute(Packages packages) {
        if(travelPackageID < packages.getSize()){
            System.out.println("Package " + travelPackageID + " found: ");
            System.out.println(packages.getPackage(travelPackageID).getCountry() + " - " + packages.getPackage(travelPackageID).getName());
        }
        else{
            System.out.println("Package not found.");
        }
    }
}



