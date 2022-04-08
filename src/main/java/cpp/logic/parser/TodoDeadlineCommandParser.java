package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.TodoDeadlineCommand;

/**
 * Parses input arguments and creates a new AddTodoDeadlineCommand object.
 */

public class TodoDeadlineCommandParser implements CommandParser<TodoDeadlineCommand> {

    /**
     * Parses the given {@code String[]} of arguments in the context of the AddTodoDeadlineCommand
     * and returns an AddTodoDeadlineCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    @Override
    public TodoDeadlineCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.FOUR_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_TODODEADLINE_COMMAND_FORMAT);
        }
        int indexProj;
        int indexTodo;
        try {
            indexProj = Integer.parseInt(userInput[1]);
            indexTodo = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.INVALID_INDEX);
        }
        if (indexProj <= 0 || indexTodo <= 0) {
            throw new IllegalCommandException(Constants.NEGATIVE_INDEX);
        }
        return new TodoDeadlineCommand(indexProj, indexTodo, userInput[3]);
    }

}
