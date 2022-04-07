//@@author 1szheng

package seedu.planitarium.parser;

import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.EmptyStringException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.global.UI;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtility {
    protected static final String FORWARD_SLASH_WARNING = UI.HORI_LINE + System.lineSeparator()
            + ">\tWarning: '%s' contains a forward slash that is not "
            + "surrounded by a space.\n>\tUse ` / ` or `\\` instead to avoid potential unintended outcomes."
            + System.lineSeparator();
    protected static final String FORWARD_SLASH = "/";
    protected static final String NOT_SPACED_FORWARD_SLASH = "(?:^/|/$|\\S+/|/\\S+)";
    protected static final Pattern PATTERN = Pattern.compile(NOT_SPACED_FORWARD_SLASH);

    // these static strings are solely for JUnit testing
    private static final String THROW_NEGATIVE_MONEY = "Money is negative";
    private static final String THROW_NOT_TWO_DP = "Money is not 2dp";

    /**
     * Returns the term surrounded by two delimiters.
     *
     * @param text          The text containing the term to be parsed.
     * @param delimiterLeft The delimiter on the left of term.
     * @return A non-delimiter-surrounded term.
     * @throws EmptyStringException if string after the left delimiter is blank.
     */
    protected static String parseDelimitedTerm(String text, String delimiterLeft)
            throws EmptyStringException {
        String[] firstParse = text.split(delimiterLeft, Constants.LIMIT_TWO_TOKENS);
        String leftRemoved;
        if (firstParse.length == Constants.LIMIT_TWO_TOKENS) {
            leftRemoved = firstParse[Constants.INDEX_LEFT_REMOVED];
        } else {
            leftRemoved = firstParse[Constants.INDEX_LEFT_NOT_EXIST];
        }
        String[] secondParse = leftRemoved.split(Parser.DELIMITER_BACK, Constants.LIMIT_TWO_TOKENS);
        String rightRemoved = secondParse[Constants.INDEX_RIGHT_REMOVED].trim();
        if (rightRemoved.isBlank()) {
            throw new EmptyStringException(delimiterLeft);
        }
        return rightRemoved;
    }

    /**
     * Returns without exception if the value provided is 0 or more.
     *
     * @param checkMoney The money value to be checked.
     * @throws NumberFormatException if the value provided is negative.
     */
    protected static void checkNegativeMoney(double checkMoney) throws NumberFormatException {
        if (Double.compare(checkMoney, Constants.MONEY_ZERO) < Constants.ZERO) {
            throw new NumberFormatException(THROW_NEGATIVE_MONEY);
        }
    }

    /**
     * Returns without exception if the value provided is at most 2 decimal places.
     *
     * @param amount The amount to be checked for 2 decimal placing.
     * @throws NumberFormatException if the value provided has more than 2 decimal places.
     */
    protected static void checkTwoDecimalPlace(String amount) throws NumberFormatException {
        if (amount.contains(Parser.DELIMITER_MONEY)) {
            int lengthWithDot = amount.length();
            int positionOfDot = amount.indexOf(Parser.DELIMITER_MONEY);
            if ((lengthWithDot - positionOfDot) > Constants.LIMIT_TWO_DECIMAL) {
                throw new NumberFormatException(THROW_NOT_TWO_DP);
            }
        }
    }

    /**
     * Returns without exception if an index is not lower than minimum accepted index value.
     *
     * @param checkIndex The index to be checked for invalid boundary.
     * @param minIndex   The minimum value that an index can be.
     * @throws IndexOutOfBoundsException if provided index is less than indicated minimum.
     */
    protected static void checkTooLowIndex(int checkIndex, int minIndex) throws IndexOutOfBoundsException {
        Parser.logger.log(Level.INFO, String.format(Parser.LOG_CHECK_INDEX_BOUNDS, checkIndex));
        if (checkIndex < minIndex) {
            Parser.logger.log(Level.WARNING, String.format(Parser.LOG_INDEX_TOO_LOW, checkIndex, minIndex));
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns without exception if an index is not higher than current maximum index value.
     *
     * @param checkIndex The index to be checked for invalid boundary.
     * @param maxIndex   The maximum value that an index can be.
     * @throws IndexOutOfBoundsException if provided index is more than indicated maximum.
     */
    protected static void checkTooHighIndex(int checkIndex, int maxIndex) throws IndexOutOfBoundsException {
        Parser.logger.log(Level.INFO, String.format(Parser.LOG_CHECK_INDEX_BOUNDS, checkIndex));
        if (checkIndex > maxIndex) {
            Parser.logger.log(
                    Level.WARNING, String.format(Parser.LOG_INDEX_TOO_HIGH, checkIndex, maxIndex));
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns without exception if user input contains one occurrence of a given delimiter.
     *
     * @param userInput User input to be checked.
     * @param delimiter A delimiter used to separate details.
     * @throws MissingDelimiterException   if user input does not contain the delimiter.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    protected static void checkContainsOnlyOneDelimiter(String userInput, String delimiter)
            throws MissingDelimiterException, DuplicateDelimiterException {
        int inputLengthWithDelimiter = userInput.length();
        int inputLengthNoDelimiter = userInput.replace(delimiter, Parser.EMPTY_STRING).length();
        int lengthOfDelimiter = delimiter.length();

        if ((inputLengthWithDelimiter - inputLengthNoDelimiter) < lengthOfDelimiter) {
            Parser.logger.log(
                    Level.WARNING, String.format(Parser.LOG_MISSING_DELIMITER, userInput, delimiter));
            throw new MissingDelimiterException(delimiter);
        }
        if ((inputLengthWithDelimiter - inputLengthNoDelimiter) > lengthOfDelimiter) {
            Parser.logger.log(
                    Level.WARNING, String.format(Parser.LOG_TOO_MANY_DELIMITER, userInput, delimiter));
            throw new DuplicateDelimiterException(delimiter);
        }
    }

    /**
     * Prints a warning if text contains a forward slash that is not enclosed by spaces.
     *
     * @param text The text to be checked.
     */
    protected static void warnIfNotSpacedForwardSlash(String text) {
        if (text.contains(FORWARD_SLASH)) {
            warnIfMatch(text);
        }
    }

    /**
     * Prints a warning if text contains a forward slash that is not enclosed by spaces.
     *
     * @param text The text to be checked.
     */
    private static void warnIfMatch(String text) {
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.find()) {
            System.out.printf(FORWARD_SLASH_WARNING, text);
        }
    }
}
