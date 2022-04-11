package seedu.duke.parser;

import seedu.duke.commands.SearchCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.duke.parser.CliSyntax.PREFIX_NAME;

/**
 * Parses input arguments and creates a new SearchCommand object.
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns an SearchCommand object for execution.
     *
     * @throws InvMgrException if the user input does not conform the expected format.
     */
    public SearchCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        Optional<String> name = argMultimap.getValue(PREFIX_NAME);
        name = convertEmptyStringToEmptyOptional(name);
        Optional<String> description = argMultimap.getValue(PREFIX_DESCRIPTION);
        description = convertEmptyStringToEmptyOptional(description);

        return new SearchCommand(name, description);
    }


    /**
     * Returns true if any one of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For SearchCommand, at least one of PREFIX_NAME and PREFIX_DESCRIPTION is needed.
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