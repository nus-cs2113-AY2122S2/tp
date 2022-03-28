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
        user.setExpenditureListArray(storage.load());
        // for now I just initialise a new CreditCardList and IncomeList, but ideally it should be loaded from storage,
        // similar to how expenditurelist is loaded.
        user.setCreditCardListArray(new CreditCardList());
        user.setIncomeListArray(new IncomeList());

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, user);
                commandType.executeCommand();

                storage.save(user.getExpenditureListArray());
                // dummy commands to save credit card and income list also
                // storage.save(user.getCreditCardListArray());
                // storage,save(user.getIncomeListArray());

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
