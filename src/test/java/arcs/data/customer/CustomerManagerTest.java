package arcs.data.customer;


import arcs.data.customer.Customer;
import arcs.data.customer.CustomerManager;
import arcs.data.exception.ArcsException;
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

    @Test
    public void findCustomer_invalidIC_exceptionThrown() {
        CustomerManager customerManager = new CustomerManager();
        Customer customer1 = new Customer("E1234567A", "Alice White",
                "09298123", "alice988@gmail.com");
        Customer customer2 = new Customer("F8391001Z", "Luke Brown",
                "93819456", "luke435@123.com");
        try {
            customerManager.addCustomer(customer1);
            customerManager.addCustomer(customer2);
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        }

        ArcsException thrown = Assertions
                .assertThrows(ArcsException.class, () -> {
                    customerManager.findCustomer("123456789");
                }, "ArcsException error was expected");
        Assertions.assertEquals("Sorry! IC number is invalid.", thrown.getMessage());
    }

    @Test
    public void findCustomer_validIC_success() {
        CustomerManager customerManager = new CustomerManager();
        Customer customer1 = new Customer("E1234567A", "Alice White",
                "09298123", "alice988@gmail.com");
        Customer customer2 = new Customer("F8391001Z", "Luke Brown",
                "93819456", "luke435@123.com");
        try {
            customerManager.addCustomer(customer1);
            customerManager.addCustomer(customer2);
            Customer found = customerManager.findCustomer(customer1.getIc());
            Assertions.assertEquals(customer1.getCustomerInfo(), found.getCustomerInfo());
        } catch (DuplicateDataException e) {
            System.out.println(e.getMessage());
        } catch (ArcsException e) {
            System.out.println(e.getMessage());
        }

    }
}
