package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.userfinancial.User;

import java.io.File;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;
    private User user;
    private final Storage storage;
    private static final String STORAGE_FILENAME = "data.txt";

    public MindMyMoney() {
        Storage savedStorage;
        ui = new Ui();
        user = new User();
        try {
            savedStorage = new Storage(new File(STORAGE_FILENAME));
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            savedStorage = null;
        }
        storage = savedStorage;
    }

    public void run() {
        ui.printIntro();

        if (storage != null) {
            try {
                user = storage.load();
            } catch (MindMyMoneyException e) {
                System.out.println(e.getMessage());
                System.out.println(System.lineSeparator());
            }
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, user);
                commandType.executeCommand();

                isExit = commandType.isExit();

                if (storage != null) {
                    storage.save(user);
                }

            } catch (MindMyMoneyException e) {
                System.out.println(e.getMessage());
                System.out.print(System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        new MindMyMoney().run();
    }
}
