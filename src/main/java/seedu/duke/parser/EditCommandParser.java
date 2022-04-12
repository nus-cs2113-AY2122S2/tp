package seedu.duke.parser;

import seedu.duke.commands.EditCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.duke.parser.CliSyntax.PREFIX_NAME;
import static seedu.duke.parser.CliSyntax.PREFIX_QUANTITY;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws InvMgrException if the user input does not conform the expected format.
     */
    public EditCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION);

        // Format violation: none of required prefixes present
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        Optional<String> optionalName = argMultimap.getValue(PREFIX_NAME);
        optionalName = convertEmptyStringToEmptyOptional(optionalName);

        Optional<Integer> optionalIntQuantity = Optional.empty();
        Optional<String> optionalStringQuantity = argMultimap.getValue(PREFIX_QUANTITY);
        optionalStringQuantity = convertEmptyStringToEmptyOptional(optionalStringQuantity);
        if (optionalStringQuantity.isPresent()) {
            optionalIntQuantity = Optional.of(
                    ParserUtils.parseQuantity(optionalStringQuantity.get()));
        }

        Optional<String> optionalDescription = argMultimap.getValue(PREFIX_DESCRIPTION);
        optionalDescription = convertEmptyStringToEmptyOptional(optionalDescription);

        int index = ParserUtils.parseIndex(argMultimap.getPreamble()) - 1;

        return new EditCommand(index,
                optionalName,
                optionalIntQuantity,
                optionalDescription);
    }

    /**
     * Returns true if any one of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For EditCommand, at least one of PREFIX_NAME, PREFIX_QUANTITY, and PREFIX_DESCRIPTION is needed.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix ->
                argumentMultimap.getValue(prefix).isPresent()
                && !argumentMultimap.getValue(prefix).get().equals(""));
    }

    /**
     * Converts 0-length {@code Optional<String>} to an empty {@code Optional}.
     *
     * @param optionalStr the {@code Optional<String>} to check.
     * @return the original {@code optionalStr}, or an empty {@code Optional}.
     */
    private static Optional<String> convertEmptyStringToEmptyOptional(Optional<String> optionalStr) {
        if (optionalStr.isPresent() && optionalStr.get().equals("")) {
            return Optional.empty();
        }
        return optionalStr;
    }

}
