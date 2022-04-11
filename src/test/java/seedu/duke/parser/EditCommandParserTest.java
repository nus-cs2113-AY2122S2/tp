package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.common.Messages;
import seedu.duke.stubs.ParserStubs;

import static seedu.duke.utils.ParserTestUtils.assertParseFailure;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class EditCommandParserTest {

    private EditCommandParser parser = new EditCommandParser();

    @Test
    void parse_compulsoryFieldsWithoutValue_throwException() {
        String testInputFormat = " %s %s %s %s";
        String testInput = String.format(testInputFormat,
                1,
                CliSyntax.PREFIX_NAME.getPrefix(),
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(),
                CliSyntax.PREFIX_QUANTITY.getPrefix());
        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_missingAllCompulsoryFields_throwException() {
        String testInputFormat = " %s";
        String testInput = String.format(testInputFormat, 1);
        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);

        String testInputWithRelativeFormat = " %s %s%s";
        String testInputWithRelative = String.format(testInputWithRelativeFormat,
                1,
                CliSyntax.PREFIX_RELATIVE.getPrefix(), "-");
        assertParseFailure(parser, testInputWithRelative, Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_relativeWithoutQuantity_throwException() {
        String testInputFormat = " %s %s%s %s%s";
        String testInput = String.format(testInputFormat,
                1,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_RELATIVE.getPrefix(), "-");
        assertParseFailure(parser, testInput, Messages.INVALID_RELATIVE_WITHOUT_QUANTITY);
    }

    @Test
    void parse_atLeastOneCompulsoryFieldPresent_success() {
        String testInputQuantityOnlyFormat = " %s %s%s";
        String testInputQuantityOnly = String.format(testInputQuantityOnlyFormat,
                1,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), ParserStubs.PAPERCUP_SETQUANTITY);
        assertParseSuccess(parser, testInputQuantityOnly, ParserStubs.ZEROINDEX_EDITCOMMAND_QUANTITYONLY);

        String testInputRelativeQuantityFormat = " %s %s%s %s%s";
        String testInputRelativeQuantity = String.format(testInputRelativeQuantityFormat,
                1,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), ParserStubs.PAPERCUP_SETRELATIVEQUANTITY,
                CliSyntax.PREFIX_RELATIVE.getPrefix(), "-");
        assertParseSuccess(parser, testInputRelativeQuantity,
                ParserStubs.ZEROINDEX_EDITCOMMAND_NEGATIVERELATIVEQUANTITY);

        String testInputNameDescMissingQuantityFormat = " %s %s%s %s%s %s";
        String testInputNameDescMissingQuantity = String.format(testInputNameDescMissingQuantityFormat,
                1,
                CliSyntax.PREFIX_NAME.getPrefix(),  ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION,
                CliSyntax.PREFIX_QUANTITY.getPrefix());
        assertParseSuccess(parser, testInputNameDescMissingQuantity,
                ParserStubs.ZEROINDEX_EDITCOMMAND_NAMEDESC_MISSINGQUANTITY);
    }
}
