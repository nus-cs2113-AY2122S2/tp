package seedu.planitarium.commands;

import seedu.planitarium.global.UI;
import seedu.planitarium.person.Family;

public class HelpCommand extends Command{

    public HelpCommand(String userInput, Family family) {
        super(userInput, family);
    }

    @Override
    public void execute(){
        UI.printHelpMsg();
    }
}
