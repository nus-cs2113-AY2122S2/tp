package seedu.duke.parser;

import seedu.duke.commands.ListFutureBorrowingsCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_BORROWER_NAME;

public class ListFutureBorrowingsParser implements Parser<ListFutureBorrowingsCommand> {

    public ListFutureBorrowingsCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BORROWER_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_BORROWER_NAME) & !args.isEmpty()) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        Optional<String> name = argMultimap.getValue(PREFIX_BORROWER_NAME);

        return new ListFutureBorrowingsCommand(name);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}