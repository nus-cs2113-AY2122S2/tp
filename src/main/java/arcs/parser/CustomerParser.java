package arcs.parser;

import arcs.commands.Command;
import arcs.commands.UndefinedCommand;
import arcs.commands.customer.AddCustomerCommand;
import arcs.commands.customer.DeleteCustomerCommand;

public class CustomerParser {
    /**
     * Parses the input string into an AddCustomerCommand.
     *
     * @param argumentLine the user input string
     * @return an AddCustomerCommand
     */
    public static Command prepareAddCustomerCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new AddCustomerCommand(null, null, null, null);
        }

        int icIdx = argumentLine.indexOf("ic/");
        int nameIdx = argumentLine.indexOf("n/");
        int phoneIdx = argumentLine.indexOf("p/");
        int emailIdx = argumentLine.indexOf("e/");

        String ic = parseField(argumentLine, icIdx, nameIdx, phoneIdx, emailIdx, 3);
        String name = parseField(argumentLine, nameIdx, icIdx, phoneIdx, emailIdx, 2);
        String phone = parseField(argumentLine, phoneIdx, icIdx, nameIdx, emailIdx, 2);
        String email = parseField(argumentLine, emailIdx, icIdx, nameIdx, phoneIdx, 2);

        return new AddCustomerCommand(ic, name, phone, email);
    }

    private static String parseField(String argumentLine, int startIdx,
                                     int fieldA, int fieldB, int fieldC, int offset) {
        String value = null;
        if (startIdx != -1) {
            int icEndIdx = argumentLine.length();
            if (fieldA > startIdx && fieldA < icEndIdx) {
                icEndIdx = fieldA;
            }
            if (fieldB > startIdx && fieldB < icEndIdx) {
                icEndIdx = fieldB;
            }
            if (fieldC > startIdx && fieldC < icEndIdx) {
                icEndIdx = fieldC;
            }
            value = argumentLine.substring(startIdx + offset, icEndIdx);
            value = value.trim();
        }
        return value;
    }

    /**
     * Parses the user input string into a DeleteCustomerCommand.
     *
     * @param argumentLine the user input string
     * @return a DeleteCustomerCommand
     */
    public static Command prepareDeleteCustomerCommand(String argumentLine) {
        if (argumentLine == null || argumentLine.isEmpty()) {
            return new UndefinedCommand("Index is not specified");
        }
        Command result;
        try {
            int index = Integer.parseInt(argumentLine);
            result = new DeleteCustomerCommand(index);
        } catch (NumberFormatException e) {
            result = new UndefinedCommand("Index should be an integer.");
        }

        return result;
    }

}
