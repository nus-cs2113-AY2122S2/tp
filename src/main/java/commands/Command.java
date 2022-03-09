package commands;

import exceptions.UnknownException;
import UI.UI;
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
    protected String replyMsg;
    protected UI GUI = new UI();
    public Command(){
    }
/**

     * Executes the instruction according to the input after parsing
     * @param Instr the instruction after parsing by parser
     * @throws UnknownException if the instruction cannot be executed

    public void execute(Parser Instr) throws UnknownException{
        //parser
        try () {
            switch () {
            case ADD_PERSON_CMD:
                //this.replyMsg =
                break;
            case DELETE_PERSON_CMD:
                break;
            case ADD_INCOME_CMD:
                break;
            case DELETE_INCOME_CMD:
                break;
            case ADD_SPENT_CMD:
                break;
            case DELETE_SPEND_CMD:
                break;
            case CALC_REMAIN:
                break;
            case LIST:
                break;
            case EXIT:
                break;
            default:
                throw new UnknownException();
            }
        } catch (UnknownException e) {
            this.replyMsg = e.toString();
        }
        GUI.printMsg(replyMsg);
    }
    **/
}