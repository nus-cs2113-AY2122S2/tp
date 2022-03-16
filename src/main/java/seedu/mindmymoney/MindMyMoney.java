package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.ExpenditureList;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;
    private ExpenditureList itemList;

    public MindMyMoney() {
        ui = new Ui();
        itemList = new ExpenditureList();
    }

    public void run() {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command commandType = Parser.parseCommand(input, itemList);
                commandType.executeCommand();
                isExit = commandType.isExit();
            } catch (MindMyMoneyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MindMyMoney().run();
    }
}
