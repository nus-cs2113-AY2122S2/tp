package seedu.duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUI {
    Scanner in;
    PrintStream out;

    public TextUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

}
