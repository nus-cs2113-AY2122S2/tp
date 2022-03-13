package commands;

import exceptions.UnknownException;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;
import ui.UI;

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
    protected String replyMsg;
    protected Parser parser = new Parser();
    protected UI ui = new UI();
    protected PersonList personList = new PersonList();

    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the instruction according to the input after parsing
     * @param userInput the instruction after parsing by parser
     * @throws UnknownException if the instruction cannot be executed
    */

    public void execute() throws UnknownException {
        String name;
        String uid;
        String amount;
        try {
            switch (parser.parseKeyword(userInput)) {
            case ADD_PERSON_CMD:
                name  = parser.parseName(userInput);
                personList.addPerson(name);
                break;
            case DELETE_PERSON_CMD:
                uid = parser.parseUserIndex(userInput);
                personList.deletePerson(uid);
                break;
            case ADD_INCOME_CMD:
                amount = parser.parseIncome(userInput);
                uid = parser.parseUserIndex(userInput);
                expenditureList.addIncome(amount, uid);
                break;
            case DELETE_INCOME_CMD:
                amount = parser.parseIncome(userInput);
                uid = parser.parseUserIndex(userInput);
                expenditureList.deleteIncome(amount, uid);
                break;
            case ADD_SPENT_CMD:
                amount = parser.parseExpenditure((userInput));
                uid = parser.parseUserIndex(userInput);
                expenditureList.addExpenditure(amount, uid);
                break;
            case DELETE_SPEND_CMD:
                amount = parser.parseExpenditure((userInput));
                uid = parser.parseUserIndex(userInput);
                expenditureList.addExpenditure(amount, uid);
                break;
            case CALC_REMAIN:
                personList.remain();
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
        } catch (UnknownException e) {
            this.replyMsg = e.toString();
        }
        ui.printMsg(replyMsg);
    }

}