package seedu.planitarium.commands;

import seedu.planitarium.exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.PersonList;

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
    protected Command newCommand;

    public CommandFactory() {
    }

    public Command getCommand(String userInput, PersonList personList) throws Exception {
        try {
            switch (Parser.parseKeyword(userInput)) {
            case ADD_PERSON_CMD:
                newCommand = new AddPersonCommand(userInput, personList);
                break;
            case DELETE_PERSON_CMD:
                newCommand = new DeletePersonCommand(userInput, personList);
                break;
            case ADD_INCOME_CMD:
            case ADD_SPENT_CMD:
                newCommand = new AddRecordCommand(userInput, personList);
                break;
            case DELETE_INCOME_CMD:
            case DELETE_SPEND_CMD:
                newCommand = new DeleteRecordCommand(userInput, personList);
                break;
            case CALC_REMAIN:
                newCommand = new RemainCommand(userInput, personList);
                break;
            case LIST:
                newCommand = new ListCommand(userInput, personList);
                break;
            case EXIT:
                newCommand = new ExitCommand(userInput, personList);
                break;
            default:
                throw new UnknownException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCommand;
    }

}
