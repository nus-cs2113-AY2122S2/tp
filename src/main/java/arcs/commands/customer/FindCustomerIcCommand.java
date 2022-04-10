package arcs.commands.customer;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;
import arcs.data.exception.ArcsException;

public class FindCustomerIcCommand extends Command {
    public static final String COMMAND_WORD = "findCustomerIc";
    private String ic;
    private static final String INVALID_IC_MESSAGE = "The IC number is invalid.";
    private static final String SUCCESS_MESSAGE = "Customer information found: ";
    private static final String EMPTY_RESULT = "No customer found with this IC.";
    private static final String NULL_MESSAGE = "IC number is not specified.";

    public FindCustomerIcCommand(String ic) {
        this.ic = ic;
    }

    @Override
    public CommandResult execute() {
        if (ic == null || ic.isEmpty()) {
            return new CommandResult(NULL_MESSAGE);
        }
        CommandResult result;
        try {
            Customer found = customerManager.findCustomer(ic);
            if (found == null) {
                result = new CommandResult(EMPTY_RESULT);
            } else {
                result = new CommandResult(SUCCESS_MESSAGE + System.lineSeparator()
                        + found.getCustomerInfo());
            }
        } catch (ArcsException e) {
            result = new CommandResult(INVALID_IC_MESSAGE);
        }

        return result;
    }
}
