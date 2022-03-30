package seedu.duke.parser;

import seedu.duke.commands.EditCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.duke.parser.CliSyntax.PREFIX_NAME;
import static seedu.duke.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.duke.parser.CliSyntax.PREFIX_RELATIVE;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws InvMgrException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION, PREFIX_RELATIVE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION)) {
            throw new InvMgrException(Messages.INVALID_COMMAND);
        }

        int index = ParserUtils.parseIndex(argMultimap.getPreamble()) - 1;
        String name = null;
        Integer quantity = null;
        String description = null;
        boolean relative = false;
        Optional<String> optionalName= argMultimap.getValue(PREFIX_NAME);
        Optional<String> optionalQuantity = argMultimap.getValue(PREFIX_QUANTITY);
        Optional<String> optionalDescription = argMultimap.getValue(PREFIX_DESCRIPTION);
        Optional<String> optionalRelative = argMultimap.getValue(PREFIX_RELATIVE);
        if (optionalName.isPresent()) {
            name = optionalName.get();
        }
        if (optionalQuantity.isPresent()) {
            quantity = ParserUtils.parseQuantity(optionalQuantity.get());
        }
        if (optionalDescription.isPresent()) {
            description = optionalDescription.get();
        }
        if (optionalRelative.isPresent()) {
            int multiplier = ParserUtils.parseRelative(optionalRelative.get());
            // this sets multiplier to -1 or 1 depending on the given relative argument. + is a multiplier of 1, - is a multiplier of -1
            quantity = quantity * multiplier;
            relative = true;
        }

        return new EditCommand(index, name, quantity, description, relative);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For EditCommand, at least one of PREFIX_NAME, PREFIX_QUANTITY, and PREFIX_DESCRIPTION is needed.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
