package seedu.duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import seedu.duke.util.StringConstants;

public class TextUi {
    protected final Scanner in;
    protected final PrintStream out;

    /**
     * Creates an instance of TextUi.
     */
    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Formats the provided message.
     *
     * @param message the message to be printed
     */
    public String formatMessage(String message) {
        return String.format("%s%s\n%s\n%s", StringConstants.LS, StringConstants.LINE, message, StringConstants.LINE);
    }

    /**
     * Receives command from user.
     *
     * @return user input
     */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Displays a message.
     */
    public void showMessage(Object message) {
        out.println(formatMessage(message.toString()));
    }

    /**
     * Displays the welcome message.
     */
    public void showHelloMessage() {
        showMessage(StringConstants.HELLO_MESSAGE);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodByeMessage() {
        showMessage(StringConstants.GOOD_BYE_MESSAGE);
    }

    /**
     * Displays the initialisation message.
     */
    public void showInitFailedMessage() {
        showMessage(StringConstants.INITIAL_FAILED_MESSAGE);
    }

}
