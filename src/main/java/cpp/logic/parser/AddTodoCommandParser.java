package cpp.logic.parser;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddTodoCommand;

public class AddTodoCommandParser implements CommandParser<AddTodoCommand> {

    @Override
    public AddTodoCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_TODO_COMMAND_FORMAT);
        }
        String todoString = parseTodoString(userInput);
        String indexString = userInput[1];
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.NON_INTEGER_INDEX);
        }
        return new AddTodoCommand(index,todoString);
    }

    private String parseTodoString(String[] strings) {
        String todoString = "";
        if (strings.length == Constants.THREE_ARGUMENTS) {
            todoString = strings[2];
        } else {
            for (int i = 2; i < strings.length; i++) {
                todoString = todoString + " " + strings[i];
            }
        }
        return todoString;
    }
}
