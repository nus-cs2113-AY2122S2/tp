package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.common.Messages;
import seedu.duke.stubs.ParserStubs;

import static seedu.duke.utils.ParserTestUtils.assertParseFailure;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class SearchCommandParserTest {
    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    void parse_compulsoryFieldsWithoutValue_throwException() {
        String testInputFormat = " %s %s %s";
        String testInput = String.format(testInputFormat,
                1,
                CliSyntax.PREFIX_NAME.getPrefix(),
                CliSyntax.PREFIX_DESCRIPTION.getPrefix());
        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_missingAllCompulsoryFields_throwException() {
        // No name and description
        String testInputFormat = " %s";
        String testInput = String.format(testInputFormat, "sdoighjosd garbage");
        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_atLeastOneCompulsoryFieldPresent_success() {
        // Name only
        String testInputNameOnlyFormat = " %s %s%s";
        String testInputNameOnly = String.format(testInputNameOnlyFormat,
                1,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME);
        assertParseSuccess(parser, testInputNameOnly, ParserStubs.SEARCHCOMMAND_NAMEONLY);

        // Name with missing description value
        String testInputNameMissingDescFormat = " %s %s%s %s";
        String testInputNameMissingDesc = String.format(testInputNameMissingDescFormat,
                1,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix());
        assertParseSuccess(parser, testInputNameMissingDesc, ParserStubs.SEARCHCOMMAND_NAMEONLY);

        // Description only
        String testInputDescriptionOnlyFormat = " %s %s%s";
        String testInputDescriptionOnly = String.format(testInputDescriptionOnlyFormat,
                1,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION);
        assertParseSuccess(parser, testInputDescriptionOnly, ParserStubs.SEARCHCOMMAND_DESCRIPTIONONLY);

        // Description with missing name value
        String testInputDescriptionMissingNameFormat = " %s %s%s %s";
        String testInputDescriptionMissingName = String.format(testInputDescriptionMissingNameFormat,
                1,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION,
                CliSyntax.PREFIX_NAME.getPrefix());
        assertParseSuccess(parser, testInputDescriptionMissingName, ParserStubs.SEARCHCOMMAND_DESCRIPTIONONLY);

        // Both name and description
        String testInputNameAndDescriptionFormat = " %s %s%s %s%s";
        String testInputNameAndDescription = String.format(testInputNameAndDescriptionFormat,
                1,
                CliSyntax.PREFIX_DESCRIPTION.getPrefix(), ParserStubs.PAPERCUP_DESCRIPTION,
                CliSyntax.PREFIX_NAME.getPrefix(), ParserStubs.PAPERCUP_NAME);
        assertParseSuccess(parser, testInputNameAndDescription, ParserStubs.SEARCHCOMMAND_NAMEANDDESCRIPTION);

    }
}
