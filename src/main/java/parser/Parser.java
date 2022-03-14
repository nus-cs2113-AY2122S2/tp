package parser;

import commands.*;
import data.exception.IllegalValueException;

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

        final String commandWord = words[0];
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

        final String category = words[0]; //either product or subscription
        final String arguments = args.replaceFirst(category, "").trim();

        String[] parts = arguments.split(" "); // i/Tom,
        //add product i/ITEM_NAME p/PRICE d/DATE t/PRODUCT_TYPE
        //add subscription i/ITEM_NAME p/PRICE d/DATE r/RENEWAL

        //make sure that the user input a name
        try {
            String name = parts[0].replace("i/", "");
        } catch (IndexOutOfBoundsException ive) {
            return new IncorrectCommand(ive.getMessage());
        }

        //make sure that the user input a price
        try {
            double price = Double.parseDouble(parts[1].replace("p/", ""));
        } catch (IndexOutOfBoundsException ive) {
            return new IncorrectCommand(ive.getMessage());
        }

        //make sure that the user input a date
        try {
            String date = parts[2].replace("i/", "");
        } catch (IndexOutOfBoundsException ive) {
            return new IncorrectCommand(ive.getMessage());
        }

        switch (category) {
            case "product":
                //make sure that the user input a product type
                try {
                    String productType = parts[3].replace("t/", "");
                } catch (IndexOutOfBoundsException ive) {
                    return new IncorrectCommand(ive.getMessage());
                }

                try {
                    AddCommand addCmd = new AddCommand();
                    addCmd.AddProductCommand(name, price, date, productType); //here the function should return a command
                    //HELP
                    //HELP
                } catch (IllegalValueException ive) {
                    return new IncorrectCommand(ive.getMessage());
                }
            case "subscription":
                //make sure that the user input a renewal date
                try {
                    String renewal = parts[3].replace("r/", "");
                } catch (IndexOutOfBoundsException ive) {
                    return new IncorrectCommand(ive.getMessage());
                }

                try {
                    return new AddSubscriptionCommand(name, price, date, renewal); //do i split AddCommand into Add subscription command and Add product Command
                    //HELP
                    //HELP
                } catch (IllegalValueException ive) {
                    return new IncorrectCommand(ive.getMessage());
                }
        }
    }

    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDeleteCommand(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
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
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
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
