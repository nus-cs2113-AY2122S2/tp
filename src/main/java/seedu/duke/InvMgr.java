package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.common.Messages;

import java.io.IOException;
import java.util.ArrayList;

public class InvMgr {
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    /**
     * Sets up the required objects, loads the user's inventory list file from the user's hard disk.
     *
     * @param filePath File path of the user's inventory list file
     * */
    public InvMgr(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            itemList = new ItemList(storage.loadData());
        } catch (InvMgrException e) {
            ui.showMessages(Messages.ERROR_MESSAGE);
            itemList = new ItemList(new ArrayList<Item>());
        }
    }

    /**
     * Greets the user and processes the user's inputs until the user issues an exit command.
     * */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.getRawUserInput();
                Command inputCommand = Parser.parse(command);
                inputCommand.execute(itemList, ui);
                isExit = inputCommand.isExit();
            } catch (InvMgrException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Main entry-point for the java.duke.InvMgr application.
     */
    public static void main(String[] args) {
        new InvMgr("data/inventoryData.json").run();
    }

}
