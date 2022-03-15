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
    protected UI ui = new UI();
    protected Person newPerson;
    protected Double amount;
    protected int uid;
    protected int index;
    protected String description;
    protected String name;
    protected PersonList personList;

    public Command(String userInput, PersonList personList) {
        this.userInput = userInput;
        this.personList = personList;
    }

    /**
     * Executes the instruction according to the input after parsing.
     * @throws UnknownException if the instruction cannot be executed.
    */

    public void execute() throws UnknownException {
        try {
            switch (Parser.parseKeyword(userInput)) {
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
        } catch (Exception e) {
            this.replyMsg = e.toString();
            ui.printMsg(replyMsg);
        }
    }

    /**
     * Deletes expenditure of someone from the list.
     * @throws Exception if the user input is invalid.
     */
    private void deleteSpend() throws Exception {
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
        newPerson = personList.getPerson(uid);
        index = Parser.getValidExpenditureIndex(Parser.parseRecordIndex(userInput), newPerson);
        newPerson.deleteExpend(index);
    }

    /**
     * Adds expenditure of someone from the list.
     * @throws Exception if the user input is invalid.
     */
    private void addSpend() throws Exception {
        description = Parser.parseDescription(userInput);
        amount = Parser.getValidMoney(Parser.parseExpenditure(userInput));
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
        newPerson = personList.getPerson(uid);
        newPerson.addExpend(description, amount);
    }

    /**
     * Deletes income of someone from the list.
     * @throws Exception if the user input is invalid.
     */
    private void deleteIncome() throws Exception {
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList) ;
        newPerson = personList.getPerson(uid);
        index = Parser.getValidIncomeIndex(Parser.parseRecordIndex(userInput), newPerson);
        newPerson.deleteIncome(index);
    }

    /**
     * Adds income of someone from the list.
     * @throws Exception is the user input is invalid.
     */

    private void addIncome() throws Exception {
        description = Parser.parseDescription(userInput);
        amount = Parser.getValidMoney(Parser.parseIncome(userInput));
        uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), personList);
        newPerson = personList.getPerson(uid);
        newPerson.addIncome(description, amount);
    }

    /**
     * Deletes a person from the list.
     * @throws Exception if the user input is invalid.
     */
    private void deletePerson() throws Exception {
        int uid = Parser.getValidUserIndex(Parser.parseUserIndex(userInput), this.personList);
        personList.removePerson(uid);
    }

    /**
     * Adds a person from the list.
     * @throws Exception if the user input is invalid.
     */
    private void addPerson() throws Exception {
        name  = Parser.parseName(userInput);
        personList.addPerson(name);
    }

}