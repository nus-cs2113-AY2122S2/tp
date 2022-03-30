package seedu.meetingjio.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static seedu.meetingjio.parser.Parser.HEADINGS_ALL;
import static seedu.meetingjio.parser.Parser.HEADINGS_WITHOUT_NAME;
import static seedu.meetingjio.parser.Parser.HEADINGS_NAME_INDEX;
import static seedu.meetingjio.parser.Parser.HEADINGS_ALL_WITH_INDEX;

import seedu.meetingjio.exceptions.MissingParameterException;

public class ParserArguments {

    protected static String[] splitArgumentsAll(String arguments) throws MissingParameterException {
        int count = HEADINGS_ALL.length;
        String[] eventDescription = new String[count];
        Arrays.fill(eventDescription, "");
        String[] splitArguments = arguments.split("[ /]");
        int index = -1;
        for (String str : splitArguments) {
            if (isHeadings(str, HEADINGS_ALL) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = isHeadings(str, HEADINGS_ALL);
                count--;
            }
        }
        if (count > 0) {
            throw new MissingParameterException();
        }
        return eventDescription;
    }

    protected static String[] splitArgumentsNameIndex(String arguments) throws MissingParameterException {
        int count = HEADINGS_NAME_INDEX.length;
        String[] eventDescription = new String[count];
        Arrays.fill(eventDescription, "");
        String[] splitArguments = arguments.split("[ /]");
        int index = -1;
        for (String str : splitArguments) {
            if (isHeadings(str, HEADINGS_NAME_INDEX) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = isHeadings(str, HEADINGS_NAME_INDEX);
                count--;
            }
        }
        if (count > 0) {
            throw new MissingParameterException();
        }
        return eventDescription;
    }

    protected static String[] splitArgumentsWithoutName(String arguments) throws MissingParameterException {
        int count = HEADINGS_WITHOUT_NAME.length;
        String[] eventDescription = new String[count];
        Arrays.fill(eventDescription, "");
        String[] splitArguments = arguments.split("[ /]");
        int index = -1;
        for (String str : splitArguments) {
            if (isHeadings(str, HEADINGS_WITHOUT_NAME) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = isHeadings(str, HEADINGS_WITHOUT_NAME);
                count--;
            }
        }
        if (count > 0) {
            throw new MissingParameterException();
        }
        return eventDescription;
    }

    // fix parameter/value missing
    protected static Map<String, String> getAttributesMap(String arguments) {
        String[] splitArguments = arguments.split("[ /]");
        Map<String, String> attributes = new HashMap<>();
        int index = -1;
        String key = "";
        String value;
        for (String str : splitArguments) {
            if (isHeadings(str, HEADINGS_ALL_WITH_INDEX) == -1) {
                value = attributes.get(key) + " " + str;
                attributes.put(key, value.trim());
            } else {
                index = isHeadings(str, HEADINGS_ALL_WITH_INDEX);
                key = HEADINGS_ALL_WITH_INDEX[index];
            }
        }
        return attributes;
    }

    protected static String getArgumentsFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }

    protected static String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    protected static int isHeadings(String str, String[] headings) {
        for (int i = 0; i < headings.length; i++) {
            if (str.equals(headings[i])) {
                return i;
            }
        }
        return -1;
    }
}
