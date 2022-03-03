package seedu.duke;


public class Duke {
    TextUI ui;
    Profile profile;

    public static void main(String[] args) {
    }

    public Duke() {
        ui = new TextUI();
        profile = new Profile();
    }
}
