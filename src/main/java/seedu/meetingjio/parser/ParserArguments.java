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

    private static String[] splitArgumentsWithHeadings(String[] splitArguments, String[] headings, 
        Boolean checkAllParams) throws MissingParameterException {
        
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

        if (checkAllParams && checkMissinglParams(count)) {
            throw new MissingParameterException();
        }
        return eventDescription;
    }

    private static boolean checkMissinglParams(int count) {
        return count > 0;
    }

    protected static String[] splitArgumentsAll(String arguments) throws MissingParameterException {
        String[] splitArguments = arguments.split("[ /]");
        return splitArgumentsWithHeadings(splitArguments, HEADINGS_ALL, true);
    }

    protected static String[] splitArgumentsNameIndex(String arguments) throws MissingParameterException {
        String[] splitArguments = arguments.split("[ /]");
        return splitArgumentsWithHeadings(splitArguments, HEADINGS_NAME_INDEX, true);
    }

    protected static String[] splitArgumentsWithoutName(String arguments) throws MissingParameterException {
        String[] splitArguments = arguments.split("[ /]");
        return splitArgumentsWithHeadings(splitArguments, HEADINGS_WITHOUT_NAME, true);
    }

    protected static Map<String, String> getAttributesMap(String arguments) throws MissingParameterException {
        String[] splitArguments = arguments.split("[ /]");
        Map<String, String> attributes = new HashMap<>();

        try {
            String[] description = splitArgumentsWithHeadings(splitArguments, HEADINGS_ALL_WITH_INDEX, false);
            for (int i = 0; i < description.length; i++) {
                if (!description[i].isEmpty()) {
                    String key = HEADINGS_ALL_WITH_INDEX[i];
                    String value = description[i];
                    attributes.put(key, value);
                }
            }
        } catch (MissingParameterException mpe) {
            // not applies to EditCommand
        }

        if (!attributes.containsKey("n") || !attributes.containsKey("i")) {
            throw new MissingParameterException();
        }
        if (attributes.size() < 3) {
            throw new MissingParameterException();
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
