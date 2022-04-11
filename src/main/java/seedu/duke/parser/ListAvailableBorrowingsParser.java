package seedu.duke.parser;

import seedu.duke.commands.ListAvailableBorrowingsCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.duke.parser.CliSyntax.PREFIX_END_DATE;

/**
 * Parses input arguments and creates a new ListAvailableBorrowingsCommand object.
 */
public class ListAvailableBorrowingsParser implements Parser<ListAvailableBorrowingsCommand> {
    
    private final String INVALID_DATE_ORDER = "Input start date has to be before end date";
    /**
     * Parses the given {@code String} of arguments in the context of the 
     * ListAvailableBorrowingsCommand and returns an ListAvailableBorrowingsCommand 
     * object for execution.
     * 
     * @throws InvMgrException if the user input does not conform the expected format
     */
    public ListAvailableBorrowingsCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = 
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        LocalDate startDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());

        assert endDate.isAfter(startDate) : "Start date must be before end date";

        if (startDate.isAfter(endDate)) {
            throw new InvMgrException(INVALID_DATE_ORDER);
        }
        return new ListAvailableBorrowingsCommand(startDate, endDate);
    }
    
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For SearchCommand, at least one of PREFIX_NAME, PREFIX_QUANTITY, and PREFIX_DESCRIPTION is needed.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}