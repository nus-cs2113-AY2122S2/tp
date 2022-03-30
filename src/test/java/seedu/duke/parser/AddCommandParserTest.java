package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.common.Messages;
import seedu.duke.data.Item;

import static seedu.duke.utils.ParserTestUtils.assertParseFailure;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    // Not all permutations were tested. Should not be needed...
    @Test
    void parse_compulsoryFieldMissing_throwException() {
        String testInputFormat = " %s %s %s %s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION);

        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_compulsoryFieldPresent_success() {
        String testInputFormat = " %s %s %s %s %s %s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), ParserStubs.PAPERCUP_QUANTITY);

        int quantity = Integer.parseInt(ParserStubs.PAPERCUP_QUANTITY);
        Item expectedItem = new Item(ParserStubs.PAPERCUP_NAME, quantity, ParserStubs.PAPERCUP_DESCRIPTION);
        Command expectedCommand = new AddCommand(expectedItem);
        assertParseSuccess(parser, testInput, expectedCommand);
    }
}
