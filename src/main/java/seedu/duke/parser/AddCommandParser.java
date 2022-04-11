package seedu.duke.parser;

import static seedu.duke.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.duke.parser.CliSyntax.PREFIX_NAME;
import static seedu.duke.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import seedu.duke.commands.AddCommand;
import seedu.duke.common.Messages;
import seedu.duke.data.Item;
import seedu.duke.exceptions.InvMgrException;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * Checks if the quantity is non-zero and non-negative before creating the command.
     *
     * @throws InvMgrException if the user input does not conform the expected format, or if the quantity indicated is
     *                         zero/negative.
     */
    public AddCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        String name = argMultimap.getValue(PREFIX_NAME).get();
        int quantity = ParserUtils.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();

        if (quantity == 0) {
            throw new InvMgrException(Messages.ZERO_QUANTITY_MESSAGE);
        }

        Item item = new Item(name, quantity, description);

        return new AddCommand(item);
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
