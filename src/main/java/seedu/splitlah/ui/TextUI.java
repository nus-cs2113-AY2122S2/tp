package seedu.splitlah.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles reading from and writing to the user interface.
 * Instantiate a TextUI object to create a user interface.
 *
 * @author Saurav
 */
public class TextUI {
    Scanner in;
    PrintStream out;

    public TextUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public TextUI() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    public void printlnMessage(String message) {
        out.println(message);
    }

    public String readNextLine() {
        return in.nextLine();
    }

    /**
     * Requests confirmation from the user via the user interface.
     * The user may confirm by inputting Y/y/yes to the interface.
     * The user may reject by inputting N/n/no to the interface.
     *
     * @param message A message to be printed initially to the user interface.
     * @return true if the user confirms
     */
    public boolean getUserConfirmation(String message) {
        printDivider();
        printlnMessage(message);
        printlnMessage(Message.PROMPT_TEXTUI_REQUEST_CONFIRMATION);
        String confirmation = readNextLine().toLowerCase();
        printDivider();
        while (true) {
            switch (confirmation) {
            case ("yes"):
            case ("y"):
                return true;
            case ("no"):
            case ("n"):
                printlnMessage(Message.ERROR_TEXTUI_USER_DID_NOT_CONFIRM);
                return false;
            default:
                printlnMessage(Message.ERROR_TEXTUI_REENTER_INPUT);
            }
        }
    }

    public void printWelcome() {
        printDivider();
        out.println(Message.LOGO);
        out.println("Welcome to Splitlah!");
        printDivider();
    }

    private void printDivider() {
        out.println("============================================================");
    }
    
    public void printHelpMenu() {
        out.println(Message.HELP_MENU);
    }

    public void printFarewell() {
        printDivider();
        out.println(Message.LOGO);
        out.println("Goodbye!");
        printDivider();
    }
}
