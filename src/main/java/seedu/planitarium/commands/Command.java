package seedu.planitarium.commands;

import seedu.planitarium.person.PersonList;
import seedu.planitarium.global.UI;

public class Command {

    protected static final String INPUT_NOT_NULL = "Input should not be empty";
    protected String type;
    protected String userInput;
    protected UI ui = new UI();
    protected PersonList personList;


    public Command(String userInput, PersonList personList) {
        this.userInput = userInput;
        this.personList = personList;
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