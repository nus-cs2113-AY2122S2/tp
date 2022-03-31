package seedu.duke.parser;

import seedu.duke.commands.ListAvailableCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.duke.parser.CliSyntax.PREFIX_END_DATE;

public class ListAvaliableCommandParser implements Parser<ListAvailableCommand> {
    
    public ListAvailableCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = 
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        LocalDate startDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtils.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());

        return new ListAvailableCommand(startDate, endDate);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}