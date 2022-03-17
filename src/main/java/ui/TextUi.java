package ui;

import static common.Messages.MESSAGE_GOODBYE;
import static common.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import commands.CommandResult;
import data.record.Record;

/**
 * Text UI of the application.
 */
public class TextUi {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A decorative prefix added to the beginning of lines printed by RecordBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     * @param version current version of the application.
     */
    public void showWelcomeMessage(String version) {
        showToUser(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }


    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(CommandResult result) {
        final List<Record> resultRecords = result.getRelevantRecords();
        if (resultRecords != null) {
            System.out.println(resultRecords);
        }
        showToUser(result.getFeedbackToUser(), DIVIDER);
    }

}

