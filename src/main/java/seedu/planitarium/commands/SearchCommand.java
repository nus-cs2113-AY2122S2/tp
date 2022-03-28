package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;

public class SearchCommand extends Command{

    protected String description;
    protected int category;
    protected

    public SearchCommand(String userInput, Family family) throws PlanITariumException {
        super(userInput, family);
        this.description = Parser.parseDescription(userInput);
        this.category = Parser.getValidCategoryIndex(Parser.parseCategoryIndex(userInput));
    }

    @Override
    public void execute(){
        family.;
    }
}
