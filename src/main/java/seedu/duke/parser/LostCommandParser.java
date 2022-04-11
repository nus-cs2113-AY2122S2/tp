package seedu.duke.parser;

import seedu.duke.commands.LostCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_ITEM_INDEX;

public class LostCommandParser implements Parser<LostCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LostCommand
     * and returns a LostCommand object for execution.
     *
     * @throws InvMgrException if the user input does not conform the expected format.
     */
    public LostCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM_INDEX);
        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_INDEX)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }
        int itemIndex = ParserUtils.parseIndex(argMultimap.getValue(PREFIX_ITEM_INDEX).get()) - 1;
        return new LostCommand(itemIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
