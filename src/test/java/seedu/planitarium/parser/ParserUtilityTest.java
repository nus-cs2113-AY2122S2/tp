//@@author 1szheng

package seedu.planitarium.parser;

import org.junit.jupiter.api.Test;
import seedu.planitarium.exceptions.EmptyStringException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserUtilityTest {

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
            ParserUtility.checkTwoDecimalPlace("1");
            ParserUtility.checkTwoDecimalPlace("1.1");
            ParserUtility.checkTwoDecimalPlace("1.11");
            ParserUtility.checkTwoDecimalPlace("12345312432.32");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkTwoDecimalPlace_notWithinTwoDecimalPlaces_exceptionThrown() {
        try {
            ParserUtility.checkTwoDecimalPlace("3.142");
            fail();
        } catch (NumberFormatException e) {
            assertEquals(MONEY_NOT_2DP, e.getMessage());
        }
        try {
            ParserUtility.checkTwoDecimalPlace("31423.1415926535");
            fail();
        } catch (NumberFormatException e) {
            assertEquals(MONEY_NOT_2DP, e.getMessage());
        }
    }
}