package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.ReturnCommand;
import seedu.duke.common.Messages;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ParserStubs;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.utils.ParserTestUtils.assertParseFailure;
import static seedu.duke.utils.ParserTestUtils.assertParseSuccess;

public class ReturnCommandParserTest {
    private ReturnCommandParser parser = new ReturnCommandParser();

    @Test
    void parse_compulsoryIndexFieldPresent_success() {
        String testInputFormat = " %s%s";
        String testInput = String.format(testInputFormat,
                CliSyntax.PREFIX_ITEM_INDEX.getPrefix(), ParserStubs.ITEM_INDEX);
         assertParseSuccess(parser, testInput, ParserStubs.RETURNCOMMAND);
    }

    @Test
    void parse_indexFieldAbsent_displaysInvalidSyntaxMessage() {
        assertParseFailure(parser, " i", Messages.INVALID_SYNTAX);
    }

    @Test
    void parse_indexOutOfRange_displaysInvalidIndexMessage() {
        assertParseFailure(parser, " i/-1", Messages.INVALID_INDEX);
    }

    @Test
    void parse_nonIntIndex_displaysInvalidIndexMessage() {
        assertParseFailure(parser, " i/abc", Messages.INVALID_INDEX);
    }

}
