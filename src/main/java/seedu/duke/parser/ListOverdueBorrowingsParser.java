package seedu.duke.parser;

import seedu.duke.commands.ListOverdueBorrowingsCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_BORROWER_NAME;

public class ListOverdueBorrowingsParser implements Parser<ListOverdueBorrowingsCommand> {

    public ListOverdueBorrowingsCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BORROWER_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_BORROWER_NAME) & !args.isEmpty()) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        Optional<String> name = argMultimap.getValue(PREFIX_BORROWER_NAME);

        return new ListOverdueBorrowingsCommand(name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For ListOverdueBorrowingsCommand, PREFIX_NAME is checked.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
