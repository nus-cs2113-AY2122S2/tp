package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.ExpenditureList;

import java.io.File;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;
    private ExpenditureList itemList;
    private final Storage storage;
    private static final String STORAGE_FILENAME = "list.txt";

    public MindMyMoney() {
        ui = new Ui();
        storage = new Storage(new File(STORAGE_FILENAME));
    }

    public void run() {
        ui.printIntro();
        itemList = storage.load();
        while (true) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, itemList);
                commandType.executeCommand();
                storage.save(itemList);
            } catch (MindMyMoneyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MindMyMoney().run();
    }
}
