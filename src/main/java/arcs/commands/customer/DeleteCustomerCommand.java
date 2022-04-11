package arcs.commands.customer;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;

public class DeleteCustomerCommand extends Command  {
    public static final String COMMAND_WORD = "deleteCustomer";
    private int index;
    private static final String SUCCESS_MESSAGE = "OK! The following customer has been deleted:";

    public DeleteCustomerCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            Customer deleted = customerManager.deleteCustomer(index);
            result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                    + deleted.getCustomerInfo());
        } catch (ArcsException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
