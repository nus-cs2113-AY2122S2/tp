package parser;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input
 * Referenced https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */
public class Parser {

    /**
     * Format to parse command by breaking it up into two segments: command and arguments: these can then be separately
     * passed into arguments.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s+(?<arguments>.*)");

    public static final Pattern ADD_COMMAND_FORMAT = Pattern.compile(
            "n/(?<itemName>.+)" + "\\s+" +
                    "sn/(?<serialNumber>.+)" + "\\s+" +
                    "t/(?<equipmentType>.+)" + "\\s+" +
                    "c/\\$?(?<cost>[\\d,\\.]+)" + "\\s+" + // TODO: maybe have error checking formula separately?
                    "pf/(?<purchasedFrom>.+)" + "\\s+" +
                    "pd/(?<purchasedDate>.+)"
    );

    public static final String MESSAGE_INCOMPLETECOMMAND_MISSING_DELIMITER =
            "Please split your command into arguments with each argument seperated by spaces!";

    public void parseCommand(String userInput) {
        // TODO: replace void with Command
        ArrayList<String> commandAndArgument;
        try {
            commandAndArgument = splitCommandTerm(userInput);
        } catch (IncompleteCommandException e) {
            System.out.println(MESSAGE_INCOMPLETECOMMAND_MISSING_DELIMITER);
            return;
        }

        switch (commandAndArgument.get(0)) {

        case "add":
            // TODO: replace "add" with constant as defined in AddCommand
            prepareAdd(commandAndArgument.get(1));

        }

    }

    private void prepareAdd(String args) {
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // validate arg string format
        if (!matcher.matches()) {
            
            return;
        }
    }

    /**
     * Break down a command into the command term to be parsed and the remainder of the arguments.
     * Assumes command term and remainder arguments are delimited by minimally one space.
     *
     * @param userInput
     * @return ArrayList of String, first element being the command term and the second element being arguments
     * @throws IncompleteCommandException if no space is found
     */
    public ArrayList<String> splitCommandTerm(String userInput) throws IncompleteCommandException {
        ArrayList<String> resultArrayList = new ArrayList<>();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        // guard against no match
        if (!matcher.matches()) {
            throw new IncompleteCommandException("Could not find space delimiter between command and arguments!");
        }
        resultArrayList.add(matcher.group("commandWord"));
        resultArrayList.add(matcher.group("arguments"));
        return resultArrayList;
    }


}
