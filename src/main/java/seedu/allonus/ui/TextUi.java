package seedu.allonus.ui;

import java.io.InputStream;
import java.util.Scanner;

public class TextUi {
    private final Scanner in;

    public TextUi() {
        this(System.in);
    }

    public TextUi(InputStream in) {
        this.in = new Scanner(in);
    }

    public String getUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }
}
