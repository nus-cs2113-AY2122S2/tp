//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.family.Family;

public abstract class Command {

    protected String type;
    protected String userInput;
    protected Family family;

    public Command(String userInput, Family family) {
        this.userInput = userInput;
        this.family = family;
        assert (userInput != null) : Constants.INPUT_NOT_NULL;
    }

    /**
     * Executes the instruction according to the input after parsing.
     * @throws PlanITariumException if the instruction cannot be executed.
    */
    public abstract void execute() throws PlanITariumException;

    public String getType() {
        return type;
    }

}