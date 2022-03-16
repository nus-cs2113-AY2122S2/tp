package seedu.duke;

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
            itemList = new ItemList(storage.loadData());
        } catch (InvMgrException e) {
            ui.showMessages("Error!");
            itemList = new ItemList(new ArrayList<Item>());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.getRawUserInput();
            Command inputCommand = Parser.parse(command);
            inputCommand.execute(itemList, ui);
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
