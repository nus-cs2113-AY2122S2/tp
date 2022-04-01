package seedu.duke.parser;

import seedu.duke.commands.CancelFutureBorrowingsCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_BORROWER_NAME;
import static seedu.duke.parser.CliSyntax.PREFIX_ITEM_INDEX;

public class CancelFutureBorrowingsParser implements Parser<CancelFutureBorrowingsCommand> {
    
    public CancelFutureBorrowingsCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = 
                ArgumentTokenizer.tokenize(args, PREFIX_BORROWER_NAME, PREFIX_ITEM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_BORROWER_NAME, PREFIX_ITEM_INDEX)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        String borrowerName = argMultimap.getValue(PREFIX_BORROWER_NAME).get();
        int borrowIndex = ParserUtils.parseIndex(argMultimap.getValue(PREFIX_ITEM_INDEX).get()) - 1;

        return new CancelFutureBorrowingsCommand(borrowerName, borrowIndex);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}