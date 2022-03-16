package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class InvMgr {
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    public InvMgr(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            itemList = new ItemList(new ArrayList<Item>());
        } catch (InvMgrException e) {
            ui.showError(e);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            Command inputCommand = Parser.parse(command);
            inputCommand.execute(ui, itemList);
            if (inputCommand instanceof ByeCommand) {
                break;
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
