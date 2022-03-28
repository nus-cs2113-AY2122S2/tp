package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.UI;
import seedu.planitarium.person.Family;

/**
 * Executes exit command and exit the program.
 */
public class ExitCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

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
