package constants;

/** Constant values used in TextUi class */
public class TextUIConstants {
    /** A decorative prefix added to the beginning of lines printed by RecordBook. */
    public static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();

    public static final String DIVIDER = "===================================================";

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    public static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /** Requests user for command. */
    public static final String USERCOMMANDREQUEST = LINE_PREFIX + "Enter command: ";

    /**
     * Wraps and echoes command.
     *
     * @param command Command to wrap and echo.
     * @return String of the wrapped command.
     */
    public static String echoCommand(String command) {
        return "[Command entered:" + command + "]";
    }
}
