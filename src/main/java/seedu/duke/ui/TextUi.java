package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import seedu.duke.exceptions.ModHappyException;




public class TextUi {

    private static final String LS = System.lineSeparator();
    private static final String LINE = "____________________________________________________________";
    private static final String HELLO_MESSAGE = "Hello, this is Mod Happy (○'◡'○)ﾉ";
    private static final String GOOD_BY_MESSAGE = "See You Later ヾ(*´▽'*)ﾉ";
    private static final String INITIAL_FAILED_MESSAGE = "Failed to start Mod Happy (..•˘_˘•..)";


    protected final Scanner in;
    protected final PrintStream out;

    /**
     * Initialzes TextUi.
     *
     * @throws ModHappyException ModHappy Exception
     */
    public TextUi() throws ModHappyException {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }


    /**
     * Formats the window style.
     *
     * @param message The message to be passed on the chat box
     * @return The formated message
     */
    public String formatMessage(String message) {
        return String.format("%s%s\n%s\n%s", LS, LINE, message, LINE);
    }

    /**
     * Receives command from user.
     *
     * @return Received command message
     */
    public String getUserCommand() {
        String newCommand = in.nextLine();
        return newCommand;
    }

    /**
     * Show the hello message to user.
     */
    public void showMessage(String message) {
        out.println(formatMessage(message));
    }

    /**
     * Show the hello message to user.
     */
    public void showHelloMessage() {
        this.showMessage(HELLO_MESSAGE);
    }

    /**
     * Show the good by message to user.
     */
    public void showGoodByeMessage() {
        this.showMessage(GOOD_BY_MESSAGE);
    }

    /**
     * Show initialize message.
     */
    public void showInitFailedMessage() {
        this.showMessage(INITIAL_FAILED_MESSAGE);
    }


}
