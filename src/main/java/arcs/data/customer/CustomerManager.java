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

    /**
     * Checks whether the IC already exists in the list.
     *
     * @param ic the input IC
     * @return true if the IC already exists.
     */
    public boolean hasDuplicateIc(String ic) {
        for (Customer customer : customers) {
            if (ic.equals(customer.getIc())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a new customer into the list.
     *
     * @param c the new customer to be added
     * @throws DuplicateDataException if the IC of the customer already exists.
     */
    public void addCustomer(Customer c) throws DuplicateDataException {
        boolean hasDuplicateCustomer = hasDuplicateIc(c.getIc());
        if (hasDuplicateCustomer) {
            throw new DuplicateDataException("This customer is already in the system.");
        }

        customers.add(c);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    /**
     * Deletes a customer at the specified index.
     * The index refers to the position of the customer in the customer list.
     *
     * @param index index of the customer tp be deleted
     * @return the deleted customer
     * @throws ArcsException if the index is equal to or less than 0, or greater than the size of the list.
     */
    public Customer deleteCustomer(int index) throws ArcsException {
        assert customers != null : "Customer list is null";
        if (index <= 0 || index > customers.size()) {
            throw new ArcsException("Index out of bound.");
        }
        Customer deleted = customers.get(index - 1);
        customers.remove(index - 1);
        return deleted;
    }

    /**
     * Finds the customer of the specified IC.
     *
     * @param ic the IC to be searched
     * @return the customer
     * @throws ArcsException if the input IC is not a valid IC.
     */
    public Customer findCustomer(String ic) throws ArcsException {
        assert ic != null : "IC is null";
        if (!Customer.isValidIc(ic)) {
            throw new ArcsException("IC number is invalid.");
        }
        for (Customer customer : customers) {
            if (ic.equals(customer.getIc())) {
                return customer;
            }
        }
        return null;
    }
}
