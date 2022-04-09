package seedu.duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import seedu.duke.util.StringConstants;

public class TextUi {
    protected static final Scanner in = new Scanner(System.in);
    protected static final PrintStream out = System.out;

    /**
     * Formats the provided message.
     *
     * @param message the message to be printed
     */
    public static String formatMessage(String message) {
        return String.format("%s%s\n%s\n%s", StringConstants.LS, StringConstants.LINE, message, StringConstants.LINE);
    }

    /**
     * Receives command from user.
     *
     * @return user input
     */
    public static String getUserCommand() {
        out.print(StringConstants.COMMAND_PROMPT);
        return in.nextLine();
    }

    /**
     * Displays a message enclosed by horizontal lines.
     */
    public static void showMessage(Object message) {
        out.println(formatMessage(message.toString()));
    }

    /**
     * Displays a message without any special formatting.
     */
    public static void showUnformattedMessage(Object message) {
        out.println(message.toString());
    }

    /**
     * Displays the welcome message.
     */
    public static void showHelloMessage() {
        showMessage(StringConstants.MESSAGE_HELLO);
    }

    /**
     * Displays the goodbye message.
     */
    public static void showGoodByeMessage() {
        showMessage(StringConstants.MESSAGE_GOODBYE);
    }


}
