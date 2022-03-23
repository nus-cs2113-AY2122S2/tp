package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.LimitCommand;

import exception.IllegalValueException;
import exception.ParseException;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX;

import static constants.ParserConstants.PARAMETER_DELIMITER_DATE;
import static constants.ParserConstants.PARAMETER_DELIMITER_ITEM_NAME;
import static constants.ParserConstants.PARAMETER_DELIMITER_PRICE;
import static constants.ParserConstants.PARAMETER_DELIMITER_PRODUCT_TYPE;
import static constants.ParserConstants.PARAMETER_DELIMITER_RENEWAL;
import static constants.ParserConstants.PARAMETER_PATTERN_DATE;
import static constants.ParserConstants.PARAMETER_PATTERN_ITEM_NAME;
import static constants.ParserConstants.PARAMETER_PATTERN_PRICE;
import static constants.ParserConstants.PARAMETER_PATTERN_PRODUCT_TYPE;
import static constants.ParserConstants.PARAMETER_PATTERN_RENEWAL;
import static constants.ParserConstants.STRING_PATTERN_PRICE;

/** Parses user input. */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput Full user input string. String should not be empty
     * @return Command based on the user input
     */
    public Command parseCommand(String userInput) {
        // splits the command word from rest of the input
        String[] words = userInput.trim().split(" ", 2);

        final String commandWord = words[0].toLowerCase(Locale.ENGLISH);

        final String arguments;
        if (words.length == 2) {
            arguments = words[1].trim();
        } else {
            arguments = null;
        }

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            if (arguments == null) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            assert arguments != null : "arguments cannot be null";
            return prepareAddCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            if (arguments == null) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
            assert arguments != null : "arguments cannot be null";
            return prepareDeleteCommand(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case LimitCommand.COMMAND_WORD:
            if (arguments == null) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LimitCommand.MESSAGE_USAGE));
            }
            assert arguments != null : "arguments cannot be null";
            return setLimitCommand(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args Full command args string
     * @return The prepared command
     */
    private Command prepareAddCommand(String args) {
        // splits the Record type from the rest of the input
        String[] words = args.trim().split(" ", 2), extractedParameters;

        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // category will have value of either "product" or "subscription"
        final String category = words[0].toLowerCase(Locale.ENGLISH);

        String parameters;
        if (words.length == 2) {
            parameters = words[1].trim();
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        assert parameters != null : "arguments cannot be null";

        // checks that "i/ITEM_NAME p/PRICE d/DATE" are present
        if (!parameters.contains(PARAMETER_DELIMITER_ITEM_NAME)
                || !parameters.contains(PARAMETER_DELIMITER_PRICE)
                || !parameters.contains(PARAMETER_DELIMITER_DATE)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        assert parameters.contains(PARAMETER_DELIMITER_ITEM_NAME) : "Command should have item name parameter";
        assert parameters.contains(PARAMETER_DELIMITER_PRICE) : "Command should have price parameter";
        assert parameters.contains(PARAMETER_DELIMITER_DATE) : "Command should have date parameter";

        // extracts item name
        extractedParameters = extractParameter(parameters, PARAMETER_PATTERN_ITEM_NAME);
        String name = extractedParameters[0].replace("i/","");
        parameters = extractedParameters[1];

        // extracts price
        extractedParameters = extractParameter(parameters, PARAMETER_PATTERN_PRICE);
        String priceString = extractedParameters[0];
        Double price = Double.parseDouble(priceString.replaceAll(STRING_PATTERN_PRICE, ""));
        parameters = extractedParameters[1];

        // extracts date
        extractedParameters = extractParameter(parameters, PARAMETER_PATTERN_DATE);
        String date = extractedParameters[0].replace("d/","");
        parameters = extractedParameters[1];

        AddCommand addCmd;

        switch (category) {
        case "product":
            // checks that "t/PRODUCT_TYPE" is present
            if (!parameters.contains(PARAMETER_DELIMITER_PRODUCT_TYPE)) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            assert parameters.contains(PARAMETER_DELIMITER_PRODUCT_TYPE) : "Command should have product type parameter";

            // extracts product type
            extractedParameters = extractParameter(parameters, PARAMETER_PATTERN_PRODUCT_TYPE);
            String productType = extractedParameters[0].replace("t/","");

            addCmd = new AddCommand();
            try {
                assert name != null : "Item name cannot be null";
                assert price != null : "price cannot be null";
                assert date != null : "date cannot be null";
                assert productType != null : "productType cannot be null";

                addCmd.AddProductCommand(name, price, date, productType);
            } catch (IllegalValueException e) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            break;

        case "subscription":
            // checks that "r/RENEWAL" is present
            if (!parameters.contains(PARAMETER_DELIMITER_RENEWAL)) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            // extracts renewal date
            extractedParameters = extractParameter(parameters, PARAMETER_PATTERN_RENEWAL);
            String renewalDate = extractedParameters[0].replace("r/","");

            addCmd = new AddCommand();
            try {
                assert name != null : "Item name cannot be null";
                assert price != null : "price cannot be null";
                assert date != null : "date cannot be null";
                assert renewalDate != null : "renewalDate cannot be null";

                addCmd.AddSubscriptionCommand(name, price, date, renewalDate);
            } catch (IllegalValueException e) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            break;

        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        return addCmd;
    }

    /**
     * Helper function to extract a substring of a specified pattern from a string.
     *
     * @param text String to search from
     * @param parameterPattern Regex pattern to search for
     * @return String array. Index 0 - extracted substring, 1 - leftover String
     */
    private String[] extractParameter(String text, String parameterPattern) {
        String[] result = new String[2];

        Pattern pattern = Pattern.compile(parameterPattern);
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        result[0] = matcher.group(0).replace("d/","");
        result[1] = text.replace(matcher.group(0),"");

        return result;
    }

    /**
     * Parses arguments in the context of the delete command.
     *
     * @param args Full command args string
     * @return The prepared command
     */
    private Command prepareDeleteCommand(String args) {
        try {
            final int targetIndex = (int) parseArgsAsDisplayedIndex(args) - 1;
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the set limit command.
     *
     * @param args Full command args string
     * @return The prepared command
     */
    private Command setLimitCommand(String args) {
        try {
            double targetLimit = parseArgsAsDisplayedIndex(args);
            return new LimitCommand(targetLimit);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LimitCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args Arguments string to parse as index number
     * @return The parsed index number
     * @throws ParseException If no region of the args string could be found for the index
     * @throws NumberFormatException The args string region is not a valid number
     */
    private double parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        if (args.isEmpty()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(args.split(" ")[0]);
    }
}
