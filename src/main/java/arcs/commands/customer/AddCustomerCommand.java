package arcs.commands.customer;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.Customer;
import arcs.data.exception.DuplicateDataException;

import java.util.ArrayList;

public class AddCustomerCommand extends Command  {
    public static final String COMMAND_WORD = "addCustomer";
    private Customer toAdd;
    private ArrayList<String> emptyFields = new ArrayList<>();
    private ArrayList<String> invalidFields = new ArrayList<>();

    private static final String SUCCESS_MESSAGE = "OK! The following new customer is added: ";
    private static final String DUPLICATE_MESSAGE = "The IC number already exits. This customer cannot be added.";
    private static final String EMPTY_FIELD_MESSAGE = "These necessary fields are not specified:";
    private static final String INVALID_FIELD_MESSAGE = "These fields are invalid:";

    public AddCustomerCommand(String ic, String name, String phone, String email) {
        checkEmptyField(ic, name, phone, email);
        if (emptyFields.isEmpty()) {
            checkInvalidField(ic, phone, email);
        }
        if (emptyFields.isEmpty() && invalidFields.isEmpty()) {
            toAdd = new Customer(ic, name, phone, email);
        }
    }

    @Override
    public CommandResult execute() {
        if (!emptyFields.isEmpty()) {
            return new CommandResult(EMPTY_FIELD_MESSAGE, emptyFields);
        }
        if (!invalidFields.isEmpty()) {
            return new CommandResult(INVALID_FIELD_MESSAGE, invalidFields);
        }

        try {
            customerManager.addCustomer(toAdd);
            return new CommandResult(SUCCESS_MESSAGE
                    + System.lineSeparator() + toAdd.getCustomerInfo());
        } catch (DuplicateDataException e) {
            return new CommandResult(DUPLICATE_MESSAGE);
        }
    }

    public void checkEmptyField(String ic, String name, String phone, String email) {
        if (ic == null || ic.isEmpty()) {
            emptyFields.add("IC");
        }
        if (name == null || name.isEmpty()) {
            emptyFields.add("Name");
        }
        if (phone == null || phone.isEmpty()) {
            emptyFields.add("Phone number");
        }
        if (email == null || email.isEmpty()) {
            emptyFields.add("Email");
        }
    }

    /**
     * Checks if the input fields are valid.
     *
     * @param ic input ic number of the customer
     * @param phone input phone number of the customer
     * @param email input email number of the customer
     */
    public void checkInvalidField(String ic, String phone, String email) {
        assert ic != null && phone != null && email != null : "Customer field is null.";
        if (!Customer.isValidIc(ic)) {
            invalidFields.add("IC");
        }
        if (!Customer.isValidPhone(phone)) {
            invalidFields.add("Phone number");
        }
        if (!Customer.isValidEmail(email)) {
            invalidFields.add("Email");
        }
    }
}
