package seedu.planitarium.commands;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

public class SearchCommand extends Command {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected String description;
    protected int category;

    public SearchCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.description = Parser.parseDescription(userInput);
        this.category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
    }

    @Override
    public void execute() {

    }
}
