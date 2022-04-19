package seedu.duke;

public class Reservation {
    private final String customerName;
    private final String contactNumber;
    private final int packageID;
    private final int numOfPax;

    public Reservation(int packageID, String customerName, String contactNumber, int numOfPax) {
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.packageID = packageID;
        this.numOfPax = numOfPax;
    }


    public int getNumOfPax() {
        return numOfPax;
    }

    public int getPackageID() {
        return packageID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return  "Package        " + getPackageID() + "\n"
                + "POC            " + getCustomerName() + "\n"
                + "Contact No.    " + getContactNumber() + "\n"
                + "Pax            " + getNumOfPax() + "\n";
    }

    public String toSave() {
        return packageID + "," + customerName + "," + contactNumber + "," + numOfPax;
    }
}
