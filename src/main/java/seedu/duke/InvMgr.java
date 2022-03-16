package seedu.duke;

import seedu.duke.commands.ByeCommand;
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
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.getRawUserInput();
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
