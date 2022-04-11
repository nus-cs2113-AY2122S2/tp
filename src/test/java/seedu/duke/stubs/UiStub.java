package seedu.duke.stubs;

import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UiStub extends Ui {

    private static List<String> outputMessages;

    public UiStub() {
        super();
        this.outputMessages = new ArrayList<>();
    }

    /**
     * Saves messages that would have been printed in Ui into a {@code List}.
     *
     * @param message Message to be displayed to user.
     * */
    @Override
    public void showMessages(String... message) {
        Collections.addAll(outputMessages, message);
    }

    public List<String> getMessages() {
        return this.outputMessages;
    }

}
