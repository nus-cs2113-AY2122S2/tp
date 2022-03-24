package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.CreditCard;

import java.io.File;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;
    private ExpenditureList itemList;
    private CreditCardList cardList;
    private final Storage storage;
    private static final String STORAGE_FILENAME = "list.txt";

    public MindMyMoney() {
        ui = new Ui();
        storage = new Storage(new File(STORAGE_FILENAME));
    }

    public void run() {
        ui.printIntro();
        itemList = storage.load();
        cardList = new CreditCardList();
        //creditCardList.add(new CreditCard("Maybank",0.05,500,500)); //command to add in a new credit card to the list!
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, itemList, cardList);
                commandType.executeCommand();
                storage.save(itemList);
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
