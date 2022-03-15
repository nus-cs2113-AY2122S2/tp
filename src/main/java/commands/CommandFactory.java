package commands;

import exceptions.UnknownException;
import seedu.planitarium.parser.Parser;

public class CommandFactory {
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

    public CommandFactory(String userInput) {
        this.userInput = userInput;
    }

    public Command getCommand() {
        Command newCommand;
        try {
            switch (Parser.parseKeyword(userInput)) {
            case ADD_PERSON_CMD:
                newCommand = new AddPersonCommand(userInput);
                break;
            case DELETE_PERSON_CMD:
                deletePerson();
                break;
            case ADD_INCOME_CMD:
                addIncome();
                break;
            case DELETE_INCOME_CMD:
                deleteIncome();
                break;
            case ADD_SPENT_CMD:
                addSpend();
                break;
            case DELETE_SPEND_CMD:
                deleteSpend();
                break;
            case CALC_REMAIN:
                personList.getRemain();
                break;
            case LIST:
                personList.list();
                break;
            case EXIT:
                ui.exit();
                System.exit(0);
                break;
            default:
                throw new UnknownException();
            }
            return newCommand;
    }
}
