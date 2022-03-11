package seedu.duke;

public class Customer {
    private String customerID;
    private String name;
    private String contactNumber;

    public Customer(String customerID, String name, String contactNumber){
        this.customerID = customerID;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getCustomerID() {
        return customerID;
    }
    public String getName() {
        return name;
    }
    public String getContactNumber() {
        return contactNumber;
    }
}
