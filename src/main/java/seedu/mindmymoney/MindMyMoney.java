package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.Lists;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;
    private Lists itemList;

    public MindMyMoney() {
        ui = new Ui();
        itemList = new Lists();
    }

    public void run() {
        ui.printIntro();

        while (true) {
            try {
                String input = ui.readInput();
                Command c = Parser.parseCommand(input, itemList);
                c.executeCommand();
            } catch (MindMyMoneyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MindMyMoney().run();
    }
}
