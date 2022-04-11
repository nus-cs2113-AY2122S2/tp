package arcs.commands.customer;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;

import java.util.ArrayList;

public class FindCustomerNameCommand extends Command {
    public static final String COMMAND_WORD = "findCustomerName";
    private final String name;
    private static final String SUCCESS_MESSAGE = "Customer information found: ";
    private static final String EMPTY_RESULT = "No customer found with this Name.";
    private static final String NULL_MESSAGE = "Name is not specified.";

    public FindCustomerNameCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        if (name == null || name.isEmpty()) {
            return new CommandResult(NULL_MESSAGE);
        }
        CommandResult result;
        ArrayList<Customer> customers = customerManager.getAllCustomers();
        ArrayList<String> customerInfo = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                customerInfo.add(customer.getCustomerInfo());
            }
        }
        if (customerInfo.size() == 0) {
            result = new CommandResult(EMPTY_RESULT);
        } else {
            result = new CommandResult(SUCCESS_MESSAGE, customerInfo);
        }

        return result;
    }
}
