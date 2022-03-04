package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles reading from and writing to the user interface.
 * Instantiate a TextUI object to create a user interface.
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

    public void printWelcome() {
        printDivider();
        out.println(" _____       _ _ _   _       _");
        out.println("/  ___|     | (_) | | |     | |");
        out.println("\\ `--. _ __ | |_| |_| | __ _| |__");
        out.println(" `--. \\ '_ \\| | | __| |/ _` | '_ \\");
        out.println("/\\__/ / |_) | | | |_| | (_| | | | |");
        out.println("\\____/| .__/|_|_|\\__|_|\\__,_|_| |_|");
        out.println("      | |");
        out.println("      |_|");
        out.println("Welcome to Splitlah!");
        printDivider();
    }

    private void printDivider() {
        out.println("============================================================");
    }

    public void printFarewell() {
        printDivider();
        out.println("_____       _ _ _   _       _");
        out.println("/  ___|     | (_) | | |     | |");
        out.println("\\ `--. _ __ | |_| |_| | __ _| |__");
        out.println(" `--. \\ '_ \\| | | __| |/ _` | '_ \\");
        out.println("/\\__/ / |_) | | | |_| | (_| | | | |");
        out.println("\\____/| .__/|_|_|\\__|_|\\__,_|_| |_|");
        out.println("      | |");
        out.println("      |_|");
        out.println("Goodbye!");
        printDivider();
    }
}
