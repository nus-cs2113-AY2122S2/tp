package seedu.duke;

import java.util.List;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private void run() {
        CommandParser commandParser = new CommandParser();
        ListContainer listContainer = new ListContainer();
        boolean shouldExitProgram = false;
        Ui ui = new Ui();
        String userInput;
        while (!shouldExitProgram) {
            try {
                userInput = ui.readUserInput();
                Command command = commandParser.parse(userInput);
                command.execute(listContainer, ui);
                shouldExitProgram = command.isExit();
            } catch (HotelLiteManagerException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
