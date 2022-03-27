package seedu.planitarium.commands;

import seedu.planitarium.global.UI;
import seedu.planitarium.person.Family;

/**
 * Executes exit command and exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand(String userInput, Family family) {
        super(userInput, family);
        this.type = "ExitCMD";
    }

    @Override
    public void execute() {
        UI.exit();
        System.out.println(UI.HORI_LINE);
        System.exit(0);
    }
}
