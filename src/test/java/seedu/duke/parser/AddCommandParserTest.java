package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.common.Messages;
import seedu.duke.stubs.ParserStubs;

import static seedu.duke.utils.ParserTestUtils.assertParseFailure;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    void parse_compulsoryFieldMissing_throwException() {
        String testInputFormat = " %s%s %s%s";
        // Missing quantity prefix
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION);
        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_compulsoryFieldPresent_success() {
        String testInputFormat = " %s%s %s%s %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), ParserStubs.PAPERCUP_QUANTITY);
        assertParseSuccess(parser, testInput, ParserStubs.PAPERCUP_ADDCOMMAND);
    }

    @Test
    void parse_invalidQuantity_throwException() {
        String testInputFormat = " %s%s %s%s %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), "asd");
        assertParseFailure(parser, testInput, Messages.INVALID_QUANTITY);
    }
}
