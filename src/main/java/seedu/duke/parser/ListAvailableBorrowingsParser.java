package seedu.duke.parser;

import seedu.duke.commands.ListAvailableBorrowingsCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.duke.parser.CliSyntax.PREFIX_END_DATE;

public class ListAvailableBorrowingsParser implements Parser<ListAvailableBorrowingsCommand> {
    
    public ListAvailableBorrowingsCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = 
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        LocalDate startDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());

        return new ListAvailableBorrowingsCommand(startDate, endDate);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}