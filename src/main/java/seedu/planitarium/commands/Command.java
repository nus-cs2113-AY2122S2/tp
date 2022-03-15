package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.person.PersonList;
import seedu.planitarium.ui.UI;

public class Command {
    protected static final String ADD_PERSON_CMD = "add";
    protected static final String DELETE_PERSON_CMD = "delete";
    protected static final String ADD_INCOME_CMD = "addin";
    protected static final String DELETE_INCOME_CMD = "deletein";
    protected static final String ADD_SPENT_CMD = "addout";
    protected static final String DELETE_SPEND_CMD = "deleteout";
    protected static final String CALC_REMAIN = "remain";
    protected static final String LIST = "list";
    protected static final String EXIT = "bye";
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
    public void execute() throws Exception {}

}