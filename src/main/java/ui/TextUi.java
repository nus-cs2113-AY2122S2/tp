package ui;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.List;
import java.util.Scanner;

import commands.CommandResult;

import data.record.Record;

import static common.Messages.MESSAGE_GOODBYE;
import static common.Messages.MESSAGE_WELCOME;

import static constants.TextUIConstants.COMMENT_LINE_FORMAT_REGEX;
import static constants.TextUIConstants.DIVIDER;
import static constants.TextUIConstants.LINE_PREFIX;
import static constants.TextUIConstants.LS;
import static constants.TextUIConstants.USER_COMMAND_REQUEST;

/**
 * Text UI of the application.
 * This is a Singleton class.
 */
public class TextUi {
    private static TextUi textUiInstance;
    private final Scanner in;
    private final PrintStream out;

    private TextUi() {
        this(System.in, System.out);
    }

    private TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns the instance of <code>TextUi</code> if it has been created. Else, creates and returns an instance of
     * <code>TextUi</code> using the System Input Stream and Output Stream.
     *
     * @return TextUi object.
     */
    public static TextUi getTextUiInstance () {
        if (textUiInstance == null) {
            textUiInstance = new TextUi();
        }
        return textUiInstance;
    }

    /**
     * Returns the instance of <code>TextUi</code> if it has been created. Else, creates and returns an instance of
     * <code>TextUi</code> using the specified Input Stream and Output Stream.
     *
     * @return TextUi object.
     */
    public static TextUi getTextUiInstance (InputStream in, PrintStream out) {
        if (textUiInstance == null) {
            textUiInstance = new TextUi(in, out);
        }
        return textUiInstance;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine Full raw user input line.
     * @return <code>true</code> if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine Full raw user input line.
     * @return <code>true</code> if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        assert COMMENT_LINE_FORMAT_REGEX instanceof String : "COMMENT_LINE_FORMAT_REGEX should be a String";

        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     *
     * @return Command (full line) entered by the user.
     */
    public String getUserCommand() {
        assert USER_COMMAND_REQUEST instanceof String : "USERCOMMANDREQUEST should be a String";

        out.print(USER_COMMAND_REQUEST);
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
     *
     * @param version Current version of the application.
     */
    public void showWelcomeMessage(String version) {
        showToUser(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                DIVIDER);
    }

    /** Prints the goodbye message upon the end of the application. */
    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }


    /** Shows message(s) to the user. */
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
            for (int resultCount = 0; resultCount < resultRecords.size(); resultCount++) {
                System.out.println(LINE_PREFIX + resultCount + 1 + ". " + resultRecords.get(resultCount).toString());
            }
        }

        showToUser(result.getFeedbackToUser(), DIVIDER);
    }
}

