package seedu.meetingjio.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static seedu.meetingjio.parser.Parser.HEADINGS_ALL;
import static seedu.meetingjio.parser.Parser.HEADINGS_WITHOUT_NAME;
import static seedu.meetingjio.parser.Parser.HEADINGS_NAME_INDEX;
import static seedu.meetingjio.parser.Parser.HEADINGS_ALL_WITH_INDEX;
import static seedu.meetingjio.parser.Parser.NAME_INDEX;
import static seedu.meetingjio.parser.Parser.INDEX_INDEX;

import seedu.meetingjio.exceptions.MissingParameterException;
import seedu.meetingjio.exceptions.ExtraParametersException;

public class ParserArguments {

    protected static String[] splitArgumentsAll(String arguments)
            throws MissingParameterException, ExtraParametersException {
        return splitAndCheckArguments(arguments, HEADINGS_ALL);
    }

    protected static String[] splitArgumentsNameIndex(String arguments)
            throws MissingParameterException, ExtraParametersException {
        return splitAndCheckArguments(arguments, HEADINGS_NAME_INDEX);
    }

    protected static String[] splitArgumentsWithoutName(String arguments)
            throws MissingParameterException, ExtraParametersException {
        return splitAndCheckArguments(arguments, HEADINGS_WITHOUT_NAME);
    }

    private static String[] splitAndCheckArguments(String arguments, String[] headings)
            throws MissingParameterException, ExtraParametersException {
        checkNumOfParams(arguments, headings);
        String[] splitArguments = arguments.split("[ /]");
        return splitArgumentsWithHeadings(splitArguments, headings);
    }

    private static String[] splitArgumentsWithHeadings(String[] splitArguments, String[] headings)  {
        int count = headings.length;
        String[] eventDescription = new String[count];
        Arrays.fill(eventDescription, "");

        int index = -1;
        for (String str : splitArguments) {
            if (isHeadings(str, headings) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = isHeadings(str, headings);
                count--;
            }
        }
        return eventDescription;
    }

    protected static Map<String, String> getAttributesMap(String arguments)
            throws MissingParameterException, ExtraParametersException {

        String[] splitArguments = arguments.split("[ /]");
        Map<String, String> attributes = new HashMap<>();

        String[] description = splitArgumentsWithHeadings(splitArguments, HEADINGS_ALL_WITH_INDEX);
        for (int i = 0; i < description.length; i++) {
            if (!description[i].isEmpty()) {
                String key = HEADINGS_ALL_WITH_INDEX[i];
                String value = description[i];
                attributes.put(key, value);
            }
        }

        if (!attributes.containsKey(HEADINGS_NAME_INDEX[NAME_INDEX]) 
            || !attributes.containsKey(HEADINGS_NAME_INDEX[INDEX_INDEX])) {
            throw new MissingParameterException();
        }

        if (attributes.size() < 3) {
            throw new MissingParameterException();
        }

        String[] splitString = arguments.split(" ");
        for (String str : splitString) {
            if (isStringContainsInvalidHeading(str, HEADINGS_ALL_WITH_INDEX)) {
                throw new ExtraParametersException();
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
        return str.trim().toLowerCase();
    }

    protected static String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    private static int isHeadings(String str, String[] headings) {
        for (int i = 0; i < headings.length; i++) {
            if (str.equals(headings[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void checkNumOfParams(String arguments, String[] headings)
        throws MissingParameterException, ExtraParametersException {

        int numOfValidParams = 0;
        String[] splitArguments = arguments.split(" ");
        for (String heading : headings) {
            if (isStringContainsHeading(splitArguments, heading)) {
                numOfValidParams += 1;
            }
        }

        if (numOfValidParams < headings.length) {
            throw new MissingParameterException();
        }

        int slashCount = 0;
        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.charAt(i) == '/') {
                slashCount += 1;
            }
        }

        if (slashCount > headings.length) {
            throw new ExtraParametersException();
        }
    }

    private static Boolean isStringContainsHeading(String[] strings, String heading) {
        String headingWithSlash = heading + "/";
        for (String str : strings) {
            if (str.length() < headingWithSlash.length()) {
                continue;
            }
            String strFront = str.substring(0, headingWithSlash.length());
            if (strFront.equals(headingWithSlash)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isStringContainsInvalidHeading(String string, String[] headings) {
        if (!string.contains("/")) {
            return false;
        }

        String[] splitString = string.split("/");
        if (splitString.length > 2) {
            return true;
        }

        for (String heading : headings) {
            if (splitString[0].length() < heading.length()) {
                continue;
            }
            String strFront = splitString[0].substring(0, heading.length());
            if (strFront.equals(heading)) {
                return false;
            }
        }
        return true;
    }
}
