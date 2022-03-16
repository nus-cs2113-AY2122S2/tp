package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.ItemList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class InvMgr {
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    public InvMgr(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        itemList = new ItemList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
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
