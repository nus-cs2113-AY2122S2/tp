package cpp.logic.parser;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.MarkCommand;

public class MarkCommandParser implements CommandParser<MarkCommand> {


    @Override
    public MarkCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_MARK_COMMAND_FORMAT);
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
        return new MarkCommand(indexProj, indexTodo);
    }
}
