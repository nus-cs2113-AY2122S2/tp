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

    public boolean doubleConfirm

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
