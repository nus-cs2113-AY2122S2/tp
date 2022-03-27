package seedu.planitarium.commands;

import seedu.planitarium.person.Family;
import seedu.planitarium.person.PersonList;
import seedu.planitarium.global.UI;

public class Command {

    protected static final String INPUT_NOT_NULL = "Input should not be empty";
    protected String type;
    protected String userInput;
    protected UI ui = new UI();
    protected Family family;


    public Command(String userInput, Family family) {
        this.userInput = userInput;
        this.family = family;
    }

    /**
     * Executes the instruction according to the input after parsing.
     * @throws Exception if the instruction cannot be executed.
    */
    public void execute() throws Exception {
    }

    public String getType() {
        return type;
    }

}