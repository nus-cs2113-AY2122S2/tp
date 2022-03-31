package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddTodoDeadlineCommand;

public class AddTodoDeadlineCommandParser implements CommandParser<AddTodoDeadlineCommand> {

    @Override
    public AddTodoDeadlineCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.FOUR_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDTODODEADLINE_COMMAND_FORMAT);
        }
        int indexProj;
        int indexTodo;
        try {
            indexProj = Integer.parseInt(userInput[1]);
            indexTodo = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.NON_INTEGER_INDEX);
        }
        if (indexProj <= 0 || indexTodo <= 0) {
            throw new IllegalCommandException(Constants.NEGATIVE_INDEX);
        }
        return new AddTodoDeadlineCommand(indexProj, indexTodo, userInput[3]);
    }

}
