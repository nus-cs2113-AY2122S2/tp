package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.LimitCommand;
import data.exception.IllegalValueException;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = words[0].toLowerCase(Locale.ENGLISH);
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return prepareAddCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case LimitCommand.COMMAND_WORD:
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
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAddCommand(String args) {
        String[] words = args.trim().split(" ", 2);  // split the input into command and arguments

        if (words.length == 0) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String category = words[0].toLowerCase(Locale.ENGLISH); //either product or subscription
        final String arguments = words[1].trim();

        // product has "t/PRODUCT TYPE"
        // subscription has "r/RENEWAL"

        // check that the common fields "i/ITEM_NAME p/PRICE d/DATE" are present
        if (!arguments.contains("i/") || !arguments.contains("p/") || !arguments.contains("d/")) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Matcher matcher;

        // extracts item name
        Pattern itemNamePattern = Pattern.compile("i/.*?(?=( [pdtr]/)|$)");
        matcher = itemNamePattern.matcher(words[1]);
        String name = matcher.group(1).replace("i/","");
        words[1] = words[1].replace(matcher.group(1),"");

        // extracts price
        Pattern pricePattern = Pattern.compile("p/.*?(?=( [pdtr]/)|$)");
        matcher = pricePattern.matcher(words[1]);
        String priceString = matcher.group(1);
        Double price = Double.parseDouble(priceString.replace("p/",""));
        words[1] = words[1].replace(matcher.group(1),"");

        // extracts date
        Pattern datePattern = Pattern.compile("d/.*?(?=( [pdtr]/)|$)");
        matcher = datePattern.matcher(words[1]);
        String date = matcher.group(1).replace("d/","");
        words[1] = words[1].replace(matcher.group(1),"");

        AddCommand addCmd;

        switch (category) {
        case "product":
            //make sure that the user input a product type
            if (!words[1].contains("t/")) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            // extracts product type
            Pattern productTypePattern = Pattern.compile("d/.*?(?=( [pdtr]/)|$)");
            matcher = productTypePattern.matcher(words[1]);
            String productType = matcher.group(1).replace("d/","");

            addCmd = new AddCommand();
            try {
                addCmd.AddProductCommand(name, price, date, productType); //here the function should return a command
            } catch (IllegalValueException e) {
                return new IncorrectCommand("Adding product went wrong!");
            }
            break;

        case "subscription":
            //make sure that the user input a renewal date
            if (!words[1].contains("r/")) {
                return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            // extracts product type
            Pattern renewalDatePattern = Pattern.compile("d/.*?(?=( [pdtr]/)|$)");
            matcher = renewalDatePattern.matcher(words[1]);
            String renewalDate = matcher.group(1).replace("d/","");

            addCmd = new AddCommand();
            try {
                addCmd.AddSubscriptionCommand(name, price, date, renewalDate); //do i split AddCommand into Add subscription command and Add product Command
            } catch (IllegalValueException e) {
                return new IncorrectCommand("Adding subscription went wrong!");
            }
            break;
        default:
            return new IncorrectCommand("Something went wrong!");
        }

        return addCmd;
    }

    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDeleteCommand(String args) {
        try {
            final int targetIndex = (int) parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    private Command setLimitCommand(String args) {
        try {
            double targetLimit = parseArgsAsDisplayedIndex(args);
            return new LimitCommand(targetLimit);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LimitCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private double parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        if (args.isEmpty()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(args.split(" ")[0]);
    }


    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

}
