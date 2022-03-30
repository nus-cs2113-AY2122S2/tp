package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.IncomeList;
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
    private static final String STORAGE_FILENAME = "list.txt";

    public MindMyMoney() {
        ui = new Ui();
        user = new User();
        storage = new Storage(new File(STORAGE_FILENAME));
    }

    public void run() {
        ui.printIntro();
        user = storage.load();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, user);
                commandType.executeCommand();

                storage.save(user);

                isExit = commandType.isExit();
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
