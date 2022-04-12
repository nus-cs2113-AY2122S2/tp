package seedu.duke.parser;

import seedu.duke.commands.BorrowCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_ITEM_INDEX;
import static seedu.duke.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.duke.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.duke.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.duke.parser.CliSyntax.PREFIX_BORROWER_NAME;

/**
 * Parses input arguments and creates a new BorrowCommand object.
 */
public class BorrowCommandParser implements Parser<BorrowCommand> {

    // Define the max no. of days a user can borrow each time
    private static final int MAX_BORROW_DURATION = 7;

    /**
     * Parses the given {@code String} of arguments in the context of the BorrowCommand
     * and returns a BorrowCommand object for execution.
     *
     * @throws InvMgrException if the user input does not conform the expected format.
     */
    public BorrowCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM_INDEX, PREFIX_QUANTITY,
                        PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_BORROWER_NAME);

        // Throw missing arguments exception
        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_INDEX, PREFIX_QUANTITY,
                PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_BORROWER_NAME)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        // Convert string commands to appropriate types. Throw exception if invalid input.
        // itemIndex is converted from 1-based to 0-based indexing.
        int itemIndex = ParserUtils.parseIndex(argMultimap.getValue(PREFIX_ITEM_INDEX).get()) - 1;
        int quantity = ParserUtils.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        LocalDate startDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        String borrowerName = argMultimap.getValue(PREFIX_BORROWER_NAME).get();

        // Throw exception if startDate > endDate
        checkDateOrder(startDate, endDate);
        // Throw exception if borrow duration > MAX_BORROW_DURATION
        checkBorrowDuration(startDate, endDate);

        return new BorrowCommand(itemIndex, quantity, startDate, endDate, borrowerName);
    }

    /**
     * Throws exception if startDate and endDate are in the wrong order.
     *
     * @param startDate Start date of borrowing.
     * @param endDate End date of borrowing.
     * @throws InvMgrException If startDate > endDate
     */
    public void checkDateOrder(LocalDate startDate, LocalDate endDate) throws InvMgrException {
        if (startDate.isAfter(endDate)) {
            throw new InvMgrException(Messages.INVALID_START_END_DATE);
        }
    }

    /**
     * Throws exception if borrow duration > MAX_BORROW_DURATION.
     *
     * @param startDate Start date of borrowing.
     * @param endDate End date of borrowing.
     * @throws InvMgrException If endDate - startDate > MAX_BORROW_DURATION.
     */
    public void checkBorrowDuration(LocalDate startDate, LocalDate endDate) throws InvMgrException {
        Period period = Period.between(startDate, endDate);
        if (period.getDays() > MAX_BORROW_DURATION) {
            throw new InvMgrException(Messages.INVALID_BORROW_DURATION);
        }
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
