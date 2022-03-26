package arcs.data.Customer;

import arcs.data.exception.DuplicateDataException;

import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();
    }

    public CustomerManager(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public boolean hasDuplicateIc(String ic) {
        for (Customer customer: customers) {
            if (ic.equals(customer.getIc())) {
                return true;
            }
        }
        return false;
    }

    public void addCustomer(Customer c) throws DuplicateDataException  {
        boolean hasDuplicateCustomer = hasDuplicateIc(c.getIc());
        if (hasDuplicateCustomer) {
            throw new DuplicateDataException("This customer is already in the system.");
        }

        customers.add(c);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }


}
