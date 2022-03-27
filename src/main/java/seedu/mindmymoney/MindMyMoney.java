package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.User;

import java.io.File;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;
    private User user;
//    private ExpenditureList itemList;
//    private CreditCardList cardList;
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
        // for now I just initialise a new CreditCardList, but ideally it should be loaded from storage,
        // similar to how expenditurelist is loaded.
        user.setCreditCardListArray(new CreditCardList());
        //creditCardList.add(new CreditCard("Maybank",0.05,500,500)); //command to add in a new credit card to the list!
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, user);
                commandType.executeCommand();
                storage.save(user.getExpenditureListArray());
                // storage.save(user.getCreditCardListArray())  dummy command to save credit card list also
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
