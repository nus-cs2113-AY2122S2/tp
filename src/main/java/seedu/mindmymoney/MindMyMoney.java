package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;

/**
 * Represents the entry point of the MindMyMoney program. Initializes the program and starts interaction with the
 * user.
 */
public class MindMyMoney {
    private final Ui ui;

    public MindMyMoney() {
        ui = new Ui();
    }

    public void run() {
        ui.printIntro();

        while (true) {
            try {
                String input = ui.readInput();
                Parser p = new Parser(input);
                Command c = p.parseCommand();
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
