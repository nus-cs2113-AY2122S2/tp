package seedu.duke;

import java.util.Arrays;

public class Parser {
    private final String command;
    private final String description;

    private static final String NAME = "n/";
    private static final String LESSON = "l/";
    private static final String DAY = "d/";
    private static final String STARTTIME = "st/";
    private static final String ENDTIME = "et/";
    private static final String MODE = "m/";
    private static final String[] HEADINGS = {NAME, LESSON, DAY, STARTTIME, ENDTIME, MODE};

    public Parser(String input) {
        this.command = getCommandFromInput(input);
        this.description = getDescriptionFromInput(input);
    }

    public String getCommand() {
        return command;
    }

    // Allows input not in specified order as long as every parameter is passed
    public String[] getAddDescription() {
        // checks empty command description error
        // checks input format error
        String[] addDescription = new String[6];
        int index = -1;
        for (String str : description.split(" ")) {
            if (checkHeadings(str) == -1) {
                addDescription[index] += " " + str;
                addDescription[index] = addDescription[index].trim();
            } else {
                index = checkHeadings(str);
                addDescription[index] = str.substring(str.indexOf("/") + 1);
            }
        }
        return addDescription;
    }

    private String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    private String getDescriptionFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }

    private int checkHeadings(String str) {
        for (int i = 0; i < HEADINGS.length; i++) {
            if (str.contains(HEADINGS[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String getDeleteString(String input){
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }
}
