package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.BorrowCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ParserStubs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class BorrowCommandParserTest {

    private BorrowCommandParser parser = new BorrowCommandParser();

    @Test
    void parse_compulsoryFieldPresent_success() throws InvMgrException {
        String testInputFormat = " %s%s %s%s %s%s %s%s %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.ITEM_INDEX,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), ParserStubs.PAPERCUP_QUANTITY,
                CliSyntax.PREFIX_START_DATE.getPrefix(), ParserStubs.BORROW_START_DATE_STR,
                CliSyntax.PREFIX_END_DATE.getPrefix(), ParserStubs.BORROW_END_DATE_STR,
                CliSyntax.PREFIX_BORROWER_NAME.getPrefix(), ParserStubs.BORROWER_NAME);
        assertParseSuccess(parser, testInput, ParserStubs.BORROWCOMMAND_ALL_FIELDS);
    }

    @Test
    void parse_start_end_wrong_order() throws InvMgrException {
        String testInputFormat = " %s%s %s%s %s%s %s%s %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.ITEM_INDEX,
                CliSyntax.PREFIX_QUANTITY.getPrefix(), ParserStubs.PAPERCUP_QUANTITY,
                CliSyntax.PREFIX_START_DATE.getPrefix(), "2022-03-10",
                CliSyntax.PREFIX_END_DATE.getPrefix(), "2022-03-09",
                CliSyntax.PREFIX_BORROWER_NAME.getPrefix(), ParserStubs.BORROWER_NAME);

        // Ensure that the correct Exception type is thrown
        InvMgrException thrown = assertThrows(InvMgrException.class, () -> parser.parse(testInput));
        // Ensure that the correct Exception message is thrown
        assertEquals(Messages.INVALID_START_END_DATE, thrown.getMessage());
    }
}
