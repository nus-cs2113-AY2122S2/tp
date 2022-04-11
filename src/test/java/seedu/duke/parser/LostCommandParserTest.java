package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.common.Messages;
import seedu.duke.stubs.ParserStubs;

import static seedu.duke.utils.ParserTestUtils.assertParseFailure;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class LostCommandParserTest {
    private LostCommandParser parser = new LostCommandParser();

    /**
     * Asserts that the correct LostCommand is created when item index and quantity are specified in the right syntax.
     * */
    @Test
    void parse_compulsoryFieldsPresent_success() {
        String testInputFormat = " %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.ITEM_INDEX);
        assertParseSuccess(parser, testInput, ParserStubs.LOST_COMMAND);
    }

    /**
     * Checks that InvMgrException is thrown when item index is not specified.
     * */
    @Test
    void parse_missingIndex_InvMgrExceptionThrown() {
        String testInput = " ";
        assertParseFailure(parser, testInput, Messages.INVALID_SYNTAX);
    }

    /**
     * Checks that InvMgrException is thrown when item index is invalid (< 0).
     * */
    @Test
    void parse_indexOutOfRange_InvMgrExceptionThrown() {
        String testInputFormat = " %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.ITEM_INDEX_OUT_OF_RANGE);
        assertParseFailure(parser, testInput, Messages.INVALID_INDEX);
    }

    /**
     * Checks that InvMgrException is thrown when item index is not an integer.
     * */
    @Test
    void parse_nonIntIndex_InvMgrExceptionThrown() {
        String testInputFormat = " %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.NON_INT_ITEM_INDEX);
        assertParseFailure(parser, testInput, Messages.INVALID_INDEX);
    }
}
