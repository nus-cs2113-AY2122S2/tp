package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.UI;
import seedu.planitarium.person.Family;

public class HelpCommand extends Command{
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    public HelpCommand(String userInput, Family family) {
        super(userInput, family);
    }

    @Override
    public void execute(){
        UI.printHelpMsg();
    }
}
