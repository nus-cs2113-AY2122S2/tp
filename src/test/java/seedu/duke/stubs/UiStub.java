package seedu.duke.stubs;

import seedu.duke.ui.Ui;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class UiStub extends Ui {

    private static Set<String> outputMessages;

    public UiStub() {
        super();
        this.outputMessages = new HashSet<>();
    }

    /**
     * Saves messages that would have been printed in Ui into a {@code List}.
     *
     * @param message Message to be displayed to user
     * */
    @Override
    public void showMessages(String... message) {
        Collections.addAll(outputMessages, message);
    }

    public Set<String> getMessages() {
        return this.outputMessages;
    }

}
