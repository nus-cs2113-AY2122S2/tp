package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.ItemList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class InvMgr {
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    /**
     * Sets up the required objects, loads the user's inventory list file from the user's hard disk
     *
     * @param filePath File path of the user's inventory list file
     * */
    public InvMgr(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            itemList = new ItemList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Greets the user and processes the user's inputs until the user issues an exit command
     * */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getRawUserInput();
            ui.showDivider();
            Command inputCommand = Parser.parse(userInput);
            inputCommand.execute(ui, itemList);
            isExit = inputCommand.isExit();
        }
    }

    /**
     * Main entry-point for the java.duke.InvMgr application.
     */
    public static void main(String[] args) {
        new InvMgr("data/inventoryData.json").run();
    }

}
