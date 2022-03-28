package seedu.meetingjio.parser;

import static seedu.meetingjio.parser.Parser.HEADINGS_ADD_LESSON;
import static seedu.meetingjio.parser.Parser.HEADINGS_ADD_MEETING;
import static seedu.meetingjio.parser.Parser.HEADINGS_DELETE_EVENT;

public class ParserArguments {

    protected String[] splitArgumentsAddLesson(String arguments) {
        String[] eventDescription = new String[6];
        String[] splitArguments = arguments.split(" ");
        int index = -1;
        for (String str : splitArguments) {
            if (containHeadings(str, HEADINGS_ADD_LESSON) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = containHeadings(str, HEADINGS_ADD_LESSON);
                eventDescription[index] = str.substring(str.indexOf("/") + 1);
            }
        }
        return eventDescription;
    }

    protected String[] splitArgumentsDeleteCommand(String arguments) {
        String[] eventDescription = new String[2];
        String[] splitArguments = arguments.split(" ");
        int index = -1;
        for (String str : splitArguments) {
            if (containHeadings(str, HEADINGS_DELETE_EVENT) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = containHeadings(str, HEADINGS_DELETE_EVENT);
                eventDescription[index] = str.substring(str.indexOf("/") + 1);
            }
        }
        return eventDescription;
    }

    protected String[] splitArgumentsAddMeeting(String arguments) {
        String[] eventDescription = new String[5];
        String[] splitArguments = arguments.split(" ");
        int index = -1;
        for (String str : splitArguments) {
            if (containHeadings(str, HEADINGS_ADD_MEETING) == -1) {
                eventDescription[index] += " " + str;
                eventDescription[index] = eventDescription[index].trim();
            } else {
                index = containHeadings(str, HEADINGS_ADD_MEETING);
                eventDescription[index] = str.substring(str.indexOf("/") + 1);
            }
        }
        return eventDescription;
    }

    protected String getArgumentsFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }

    protected String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    protected int containHeadings(String str, String[] headings) {
        for (int i = 0; i < headings.length; i++) {
            if (str.contains(headings[i])) {
                int headingLength = headings[i].length();
                String subStr = str.substring(0, headingLength);
                if (subStr.equals(headings[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
