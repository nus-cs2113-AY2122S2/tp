package arcs.commands.customer;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;

import java.util.ArrayList;

public class ListCustomerCommand extends Command {
    public static final String COMMAND_WORD = "listCustomer";
    private static final String FEEDBACK = "Registered customers:";

    @Override
    public CommandResult execute() {
        ArrayList<Customer> customers = customerManager.getAllCustomers();
        ArrayList<String> customerInfo = new ArrayList<>();
        for (Customer customer: customers) {
            customerInfo.add(customer.getCustomerInfo());
        }
        return new CommandResult(FEEDBACK, customerInfo);
    }
}
