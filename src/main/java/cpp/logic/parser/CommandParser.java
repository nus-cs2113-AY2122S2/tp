package cpp.logic.parser;

import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.Command;

public interface CommandParser<T extends Command> {

    T parse(String[] userInput) throws IllegalCommandException;
}
