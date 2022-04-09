package seedu.duke.parser;

import seedu.duke.commands.ReturnCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;
import static seedu.duke.parser.CliSyntax.PREFIX_ITEM_INDEX;
import java.util.stream.Stream;



public class ReturnCommandParser implements Parser<ReturnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReturnCommand
     * and returns a ReturnCommand object for execution.
     * @throws InvMgrException if the user's input does not adhere to the expected command format
     */
    public ReturnCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM_INDEX);
        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_INDEX)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }
        int itemIndex = ParserUtils.parseIndex(argMultimap.getValue(PREFIX_ITEM_INDEX).get()) - 1;
        return new ReturnCommand(itemIndex);
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}