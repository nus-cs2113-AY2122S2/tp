package seedu.meetingjio;

public class Parser {
    private final String command;
    private final String description;
    private static final String ADD_COMMAND = "add";

    public Parser(String input) {
        this.command = getCommandFromInput(input);
        this.description = getDescriptionFromInput(input);
    }

    public String getCommand() {
        return command;
    }

    private static String getCommandFromInput(String input) {
        return input.split(" ", 1)[0].trim();
    }

    private static String getDescriptionFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }
}
