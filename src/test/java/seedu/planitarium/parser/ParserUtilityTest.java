//@@author 1szheng

package seedu.planitarium.parser;

import org.junit.jupiter.api.Test;
import seedu.planitarium.exceptions.EmptyStringException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserUtilityTest {

    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String ERROR_MSG = "Unknown error is detected from '%s', please check again.";
    private static final String ADD = "add";
    private static final String DELIMITER_N = "/n";
    private static final String ADD_N_BILL = "add /n bill";
    private static final String ADD_N_BILL_G_1 = "add /n bill /g 1";
    private static final String BILL = "bill";
    private static final String EMPTY_AFTER_DELIMITER = "add /n  /e";
    private static final String EMPTY_STRING_AFTER_N = "Empty string after `/n`";
    private static final double POSITIVE_MONEY = 1.0;
    private static final int NEGATIVE_MONEY = -1;
    private static final String MONEY_IS_NEGATIVE = "Money is negative";
    private static final String MONEY_NOT_2DP = "Money is not 2dp";
    private static final String WHOLE_NUMBER = "1";
    private static final String ONE_DP = "1.1";
    private static final String TWO_DP = "1.11";
    private static final String TWO_DP_LONG = "12345312432.32";
    private static final String THREE_DP = "3.142";
    private static final String MANY_DP = "31423.1415926535";
    private static final String SPACED_FORWARD_SLASH = " / ";
    private static final String S_SPACE_SLASH_SPACE_O = "S / O";
    private static final String S_SLASH_O = "S/O";
    private static final String START_WITH_SLASH = "/ O";
    private static final String END_WITH_SLASH = "S /";
    private static final String NO_LEFT_SPACE_SLASH = "S/ ";
    private static final String NO_RIGHT_SPACE_SLASH = " /O";

    @Test
    void parseDelimitedTerm_delimitedTerm_success() throws EmptyStringException {
        String output1 = ParserUtility.parseDelimitedTerm(ADD, DELIMITER_N);
        assertEquals(ADD, output1);

        String output2 = ParserUtility.parseDelimitedTerm(ADD_N_BILL, DELIMITER_N);
        assertEquals(BILL, output2);

        String output3 = ParserUtility.parseDelimitedTerm(ADD_N_BILL_G_1, DELIMITER_N);
        assertEquals(BILL, output3);
    }

    @Test
    void parseDelimitedTerm_emptyTerm_exceptionThrown() {
        try {
            ParserUtility.parseDelimitedTerm(EMPTY_AFTER_DELIMITER, DELIMITER_N);
        } catch (EmptyStringException e) {
            assertEquals(String.format(ERROR_MSG, EMPTY_STRING_AFTER_N), e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkNegativeMoney_positiveMoney_success() {
        ParserUtility.checkNegativeMoney(POSITIVE_MONEY);
    }

    @Test
    void checkNegativeMoney_negativeMoney_exceptionThrown() {
        try {
            ParserUtility.checkNegativeMoney(NEGATIVE_MONEY);
        } catch (NumberFormatException e) {
            assertEquals(MONEY_IS_NEGATIVE, e.getMessage());
        }
    }

    @Test
    void checkTwoDecimalPlace_withinTwoDecimalPlaces_success() {
        try {
            ParserUtility.checkTwoDecimalPlace(WHOLE_NUMBER);
            ParserUtility.checkTwoDecimalPlace(ONE_DP);
            ParserUtility.checkTwoDecimalPlace(TWO_DP);
            ParserUtility.checkTwoDecimalPlace(TWO_DP_LONG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkTwoDecimalPlace_notWithinTwoDecimalPlaces_exceptionThrown() {
        try {
            ParserUtility.checkTwoDecimalPlace(THREE_DP);
            fail();
        } catch (NumberFormatException e) {
            assertEquals(MONEY_NOT_2DP, e.getMessage());
        }
        try {
            ParserUtility.checkTwoDecimalPlace(MANY_DP);
            fail();
        } catch (NumberFormatException e) {
            assertEquals(MONEY_NOT_2DP, e.getMessage());
        }
    }

    @Test
    void warnIfNotSpacedForwardSlash_hasSpacedForwardSlash_success() {
        ParserUtility.warnIfNotSpacedForwardSlash(SPACED_FORWARD_SLASH);
        ParserUtility.warnIfNotSpacedForwardSlash(S_SPACE_SLASH_SPACE_O);
    }

    @Test
    void warnIfNotSpacedForwardSlash_hasNoSpacedForwardSlash_success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));

        ParserUtility.warnIfNotSpacedForwardSlash(S_SLASH_O);
        assertEquals(String.format(ParserUtility.FORWARD_SLASH_WARNING, S_SLASH_O), newOut.toString());
        newOut.reset();

        ParserUtility.warnIfNotSpacedForwardSlash(START_WITH_SLASH);
        assertEquals(String.format(ParserUtility.FORWARD_SLASH_WARNING, START_WITH_SLASH), newOut.toString());
        newOut.reset();

        ParserUtility.warnIfNotSpacedForwardSlash(END_WITH_SLASH);
        assertEquals(String.format(ParserUtility.FORWARD_SLASH_WARNING, END_WITH_SLASH), newOut.toString());
        newOut.reset();

        ParserUtility.warnIfNotSpacedForwardSlash(NO_LEFT_SPACE_SLASH);
        assertEquals(String.format(ParserUtility.FORWARD_SLASH_WARNING, NO_LEFT_SPACE_SLASH), newOut.toString());
        newOut.reset();

        ParserUtility.warnIfNotSpacedForwardSlash(NO_RIGHT_SPACE_SLASH);
        assertEquals(String.format(ParserUtility.FORWARD_SLASH_WARNING, NO_RIGHT_SPACE_SLASH), newOut.toString());
        newOut.reset();

        System.setOut(ORIGINAL_OUT);
    }
}