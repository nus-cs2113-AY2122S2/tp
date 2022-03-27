package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;
import seedu.planitarium.ui.UI;

/**
 * Executes exit command and exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand(String userInput, PersonList personList) {
        super(userInput, personList);
        this.type = "ExitCMD";
    }

    @Override
    public void execute() {
        UI.exit();
        System.out.println(UI.HORI_LINE);
        System.exit(0);
    }
}
