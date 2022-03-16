package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;

/**
 * Executes exit command and exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand(String userInput, PersonList personList) {
        super(userInput, personList);
    }

    @Override
    public void execute() {
        ui.exit();
        System.out.println(ui.HORI_LINE);
        System.exit(0);
    }
}
