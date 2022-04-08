package arcs.parser;

import arcs.commands.Command;
import arcs.commands.CommandResult;
import arcs.data.customer.CustomerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerParserTest {
    @Test
    public void prepareAddCustomerCommand_orderedInput_success() {
        CustomerManager customerManager = new CustomerManager();
        Command addCustomerCommand = CustomerParser
                .prepareAddCustomerCommand("ic/W9248013B n/Eddie Lee p/38201843 e/eddie1238@123.com");
        addCustomerCommand.setData(null, null, null, customerManager,
                null, null);
        CommandResult result = addCustomerCommand.execute();
        String expectedFeedback = "OK! The following new customer is added: " + System.lineSeparator()
                + "IC: W9248013B" + System.lineSeparator()
                + "Name: Eddie Lee" + System.lineSeparator()
                + "Phone number: 38201843" + System.lineSeparator()
                + "Email address: eddie1238@123.com";
        Assertions.assertEquals(expectedFeedback, result.getFeedbackToUser());

    }

    @Test
    public void prepareAddCustomerCommand_randomOrder_success() {
        CustomerManager customerManager = new CustomerManager();
        Command addCustomerCommand = CustomerParser
                .prepareAddCustomerCommand("e/eddie1238@123.com ic/W9248013B p/38201843 n/Eddie Lee");
        addCustomerCommand.setData(null, null, null, customerManager,
                null, null);
        CommandResult result = addCustomerCommand.execute();
        String expectedFeedback = "OK! The following new customer is added: " + System.lineSeparator()
                + "IC: W9248013B" + System.lineSeparator()
                + "Name: Eddie Lee" + System.lineSeparator()
                + "Phone number: 38201843" + System.lineSeparator()
                + "Email address: eddie1238@123.com";
        Assertions.assertEquals(expectedFeedback, result.getFeedbackToUser());

    }

    @Test
    public void prepareAddCustomerCommand_missingFields_failure() {
        CustomerManager customerManager = new CustomerManager();
        Command addCustomerCommand = CustomerParser
                .prepareAddCustomerCommand("e/eddie1238@123.com p/38201843 n/Eddie Lee");
        addCustomerCommand.setData(null, null, null, customerManager,
                null, null);
        CommandResult result = addCustomerCommand.execute();
        String expectedFeedback = "These necessary fields are not specified:";
        Assertions.assertEquals(expectedFeedback, result.getFeedbackToUser());

    }

    @Test
    public void prepareAddCustomerCommand_invalidFields_failure() {
        CustomerManager customerManager = new CustomerManager();
        Command addCustomerCommand = CustomerParser
                .prepareAddCustomerCommand("ic/W9248013B e/eddie1238123.com p/3820184W n/Eddie Lee");
        addCustomerCommand.setData(null, null, null, customerManager,
                null, null);
        CommandResult result = addCustomerCommand.execute();
        String expectedFeedback = "These fields are invalid:";
        Assertions.assertEquals(expectedFeedback, result.getFeedbackToUser());

    }
}
