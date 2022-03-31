package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.BorrowCommand;
import seedu.duke.exceptions.InvMgrException;

import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class BorrowCommandParserTest {

    private BorrowCommandParser parser = new BorrowCommandParser();

    @Test
    void parse_compulsoryFieldPresent_success() throws InvMgrException {
        String testInputFormat = " %s%s %s%s %s%s %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.ITEM_INDEX,
                CliSyntax.PREFIX_START_DATE.getPrefix(), ParserStubs.BORROW_START_DATE,
                CliSyntax.PREFIX_END_DATE.getPrefix(), ParserStubs.BORROW_END_DATE,
                CliSyntax.PREFIX_BORROWER_NAME.getPrefix(), ParserStubs.BORROWER_NAME);
        assertParseSuccess(parser, testInput, ParserStubs.BORROWCOMMAND_ALL_FIELDS);
    }
}
