package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;

public class Duke {
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    public Duke(String filePath) {
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
            if (inputCommand instanceof ByeCommand) {
                break;
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke("data/inventoryData.json").run();
    }
}
