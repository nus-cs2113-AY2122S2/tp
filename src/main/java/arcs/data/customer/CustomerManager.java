package arcs.data.customer;

import arcs.data.exception.ArcsException;
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

    public Customer deleteCustomer(int index) throws ArcsException {
        assert customers != null : "Customer list is null";
        if (index <= 0 || index > customers.size()) {
            throw new ArcsException("Index out of bound.");
        }
        Customer deleted = customers.get(index - 1);
        customers.remove(index - 1);
        return deleted;
    }

    public Customer findCustomer(String ic) throws ArcsException {
        assert ic != null : "IC is null";
        if (!Customer.isValidIc(ic)) {
            throw new ArcsException("IC number is invalid.");
        }
        for (Customer customer: customers) {
            if (ic.equals(customer.getIc())) {
                return customer;
            }
        }
        return null;
    }


}
