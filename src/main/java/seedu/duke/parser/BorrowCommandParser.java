package seedu.duke.parser;

import seedu.duke.commands.BorrowCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new BorrowCommand object.
 */
public class BorrowCommandParser implements Parser<BorrowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BorrowCommand
     * and returns a BorrowCommand object for execution.
     * @throws InvMgrException if the user input does not conform the expected format
     */
    public BorrowCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM_INDEX, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_BORROWER_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_INDEX, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_BORROWER_NAME)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        int itemIndex = ParserUtils.parseIndex(argMultimap.getValue(PREFIX_ITEM_INDEX).get()) - 1;
        String startDateStr = argMultimap.getValue(PREFIX_START_DATE).get();
        String endDateStr = argMultimap.getValue(PREFIX_END_DATE).get();
        String borrowerName = argMultimap.getValue(PREFIX_BORROWER_NAME).get();

        return new BorrowCommand(itemIndex, startDateStr, endDateStr, borrowerName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For AddCommand, all of PREFIX_NAME, PREFIX_QUANTITY, and PREFIX_DESCRIPTION is needed.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
