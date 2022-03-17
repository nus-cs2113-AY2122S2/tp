package seedu.duke;

public class Reservation {
    private int reservationID;
    private String customerName;
    private String contactNumber;
    private int packageID;
    private int numOfPax;

    public Reservation(int reservationID, int packageID, String customerName, String contactNumber, int numOfPax){
        this.reservationID = reservationID;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.packageID = packageID;
        this.numOfPax = numOfPax;
    }

    public int getReservationID() {
        return reservationID;
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
        return "Reservation '" + getReservationID() + "'\n" +
                "Package        " + getPackageID() + "\n" +
                "POC            " + getCustomerName() + "\n" +
                "Contact No.    " + getContactNumber() + "\n" +
                "Pax            " + getNumOfPax() + "\n"
                ;
    }
}
