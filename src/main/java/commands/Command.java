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
     * @throws UnknownException if the instruction cannot be executed
    */

    public void execute() throws UnknownException {
        try {
            switch (parser.parseKeyword(userInput)) {
            case ADD_PERSON_CMD:
                addPerson();
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
        } catch (UnknownException e) {
            this.replyMsg = e.toString();
        }
        ui.printMsg(replyMsg);
    }

    private void deleteSpend() {
        Person newPerson;
        String uid;
        int index;
        uid = parser.parseUserIndex(userInput);
        index = parser.parseRecIndex(userInput);
        newPerson = personList.getPerson(uid);
        newPerson.deleteExpend(index);
    }

    private void addSpend() {
        String amount;
        String uid;
        Person newPerson;
        String description;
        description = parser.parseDescription(userInput);
        amount = parser.parseExpenditure((userInput));
        uid = parser.parseUserIndex(userInput);
        newPerson = personList.getPerson(uid);
        newPerson.addExpend(description, amount);
    }

    private void deleteIncome() {
        Person newPerson;
        String uid;
        int index;
        uid = parser.parseUserIndex(userInput);
        index = parser.parseRecIndex(userInput);
        newPerson = personList.getPerson(uid);
        newPerson.deleteIncome(index);
    }

    private void addIncome() {
        String uid;
        String amount;
        Person newPerson;
        String description;
        description = parser.parseDescription(userInput);
        amount = parser.parseIncome(userInput);
        uid = parser.parseUserIndex(userInput);
        newPerson = personList.getPerson(uid);
        newPerson.addIncome(description, amount);
    }

    private void deletePerson() throws Exception{
        try {
            int uid = parser.checkValidUserIndex(parser.parseUserIndex(userInput), this.personList);
            personList.removePerson(uid);
        } catch (Exception e) {
            throw new UnknownException();
        }
    }

    private void addPerson() {
        String name;
        name  = parser.parseName(userInput);
        personList.addPerson(name);
    }

}