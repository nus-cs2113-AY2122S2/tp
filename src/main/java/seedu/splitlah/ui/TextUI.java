package seedu.splitlah.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents a user interface that reads from and writes to the command line.
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

    public void printMessage(String message) {
        out.print(message);
    }

    public void printlnMessage(String message) {
        out.println(message);
    }

    public void printlnMessageWithDivider(String message) {
        out.println(message);
        printDivider();
    }

    public String readNextLine() {
        printMessage(Message.PROMPT_TEXTUI_AWAITING_INPUT);
        return in.nextLine();
    }

    /**
     * Requests confirmation from the user via the user interface.
     * The user may confirm by inputting Y/y/yes to the interface.
     * The user may reject by inputting N/n/no to the interface.
     *
     * @param message A String object to be printed initially to the user interface.
     * @return true if the user confirms
     */
    public boolean getUserConfirmation(String message) {
        printDivider();
        printlnMessage(message);
        String confirmation = readNextLine().toLowerCase();
        printDivider();
        while (true) {
            switch (confirmation) {
            case ("yes"):
                // fallthrough
            case ("y"):
                return true;
            case ("no"):
                // fallthrough
            case ("n"):
                printlnMessage(Message.ERROR_TEXTUI_USER_DID_NOT_CONFIRM);
                return false;
            default:
                printlnMessage(Message.ERROR_TEXTUI_REENTER_INPUT);
                confirmation = readNextLine().toLowerCase();
            }
        }
    }

    public void printWelcome() {
        printDivider();
        out.println(Message.ASCII_TEXTUI_LOGO);
        out.println("Welcome to Splitlah!");
        printDivider();
    }

    private void printDivider() {
        out.println("============================================================");
    }

    public void printDashDivider() {
        out.println("------------------------------------------------------------");
    }
    
    public void printHelpMenu() {
        out.println(Message.MESSAGE_TEXTUI_HELP_MENU);
    }

    public void printFarewell() {
        printDivider();
        out.println(Message.ASCII_TEXTUI_LOGO);
        out.println("Goodbye!");
        printDivider();
    }
}
