package parser;

import java.util.ArrayList;
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


    /**
     * Break down a command into the command term to be parsed and the remainder of the arguments.
     * Assumes command term and remainder arguments are delimited by minimally one space.
     * @param userInput
     * @return ArrayList of String, first element being the command term and the second element being arguments
     */
    public ArrayList<String> parseCommand(String userInput) {
        ArrayList<String> resultArrayList = new ArrayList<>();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        // guard against no match
        if (!matcher.matches()) {
            System.out.println("Invalid input"); // TODO: print correct command
            return resultArrayList;
        }
        resultArrayList.add(matcher.group("commandWord"));
        resultArrayList.add(matcher.group("arguments"));
        return resultArrayList;
    }

}
