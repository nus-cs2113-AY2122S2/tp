package seedu.duke;


public class Duke {
    TextUI ui;
    Profile profile;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        ui = new TextUI();
        profile = new Profile();
    }
    private void run() {
        showWelcomeMessage();
        runProcessLoop();
        showFarewellMessage();
    }
    private void showWelcomeMessage() {
        ui.printWelcome();
    }
    private void showFarewellMessage() {
        ui.printFarewell();
        System.exit(0);
    }
    private void runProcessLoop() {
        String userInput;
        do {
            userInput = ui.readNextLine();
        } while (userInput.contains("Bye"));
    }
}
