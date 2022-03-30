package seedu.duke;

import java.io.IOException;

import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private void run() {
        Ui ui = new Ui();
        ui.printGreeting();
        CommandParser commandParser = new CommandParser();
        ListContainer listContainer = null;
        try {
            listContainer = new ListContainer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HotelLiteManagerException e) {
            ui.printErrorMessage(e);
        }

        boolean shouldExitProgram = false;
        String userInput;
        while (!shouldExitProgram) {
            try {
                userInput = ui.readUserInput();
                Command command = commandParser.parse(userInput);
                command.execute(listContainer, ui);
                shouldExitProgram = command.isExit();
            } catch (HotelLiteManagerException e) {
                ui.printErrorMessage(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
