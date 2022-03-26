package arcs.data.Customer;

import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();
    }

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
