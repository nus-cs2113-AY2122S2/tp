package seedu.duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import seedu.duke.exceptions.ModHappyException;

public class TextUi {

    private static final String LS = System.lineSeparator();
    private static final String LINE = "____________________________________________________________";
    private static final String HELLO_MESSAGE = "Hello, this is Mod Happy (○'◡'○)ﾉ";
    private static final String GOOD_BY_MESSAGE = "See you later ヾ(*´▽'*)ﾉ";
    private static final String INITIAL_FAILED_MESSAGE = "Failed to start Mod Happy (..•˘_˘•..)";

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
        return String.format("%s%s\n%s\n%s", LS, LINE, message, LINE);
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
     * Display a message.
     */
    public void showMessage(Object message) {
        out.println(formatMessage(message.toString()));
    }

    /**
     * Display the welcome message.
     */
    public void showHelloMessage() {
        showMessage(HELLO_MESSAGE);
    }

    /**
     * Display the goodbye message.
     */
    public void showGoodByeMessage() {
        showMessage(GOOD_BY_MESSAGE);
    }

    /**
     * Display the initialisation message.
     */
    public void showInitFailedMessage() {
        showMessage(INITIAL_FAILED_MESSAGE);
    }

}
