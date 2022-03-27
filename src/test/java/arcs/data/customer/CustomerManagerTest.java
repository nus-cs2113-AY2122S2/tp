package arcs.data.customer;


import arcs.data.customer.Customer;
import arcs.data.customer.CustomerManager;
import arcs.data.exception.DuplicateDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerManagerTest {
    @Test
    public void addCustomer_duplicateCustomer_failure() {
        CustomerManager customerManager = new CustomerManager();
        Customer customer1 = new Customer("E1234567A", "Alice White",
                "09298123", "alice988@gmail.com");
        Customer customer2 = new Customer("E1234567A", "Tom",
                "98761234", "tom12@123.com");
        try {
            customerManager.addCustomer(customer1);
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        }
        DuplicateDataException thrown = Assertions
                .assertThrows(DuplicateDataException.class, () -> {
                    customerManager.addCustomer(customer2);
                }, "DuplicateDataException error was expected");
        Assertions.assertEquals("Sorry! This customer is already in the system.", thrown.getMessage());
    }

    @Test
    public void addCustomer_randomOrder_success() {
        CustomerManager customerManager = new CustomerManager();
        Customer customer1 = new Customer("E1234567A", "Alice White",
                "09298123", "alice988@gmail.com");
        try {
            customerManager.addCustomer(customer1);
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(customer1.getCustomerInfo(),
                customerManager.getAllCustomers().get(0).getCustomerInfo());
    }
}
