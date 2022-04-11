package seedu.duke.parser;

import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParserUtils {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex the index to parse.
     * @return an integer representing the integer value of {@code oneBasedIndex}.
     * @throws InvMgrException if {@code oneBasedIndex} could not be parsed into a valid one-based index integer.
     */
    public static int parseIndex(String oneBasedIndex) throws InvMgrException {
        String trimmedIndex = oneBasedIndex.trim();
        try {
            int value = Integer.parseInt(trimmedIndex);
            if (value <= 0) {
                throw new InvMgrException(Messages.INVALID_INDEX);
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new InvMgrException(Messages.INVALID_INDEX);
        }
    }

    /**
     * Parses {@code quantity} into an {@code int} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param quantity the quantity to parse.
     * @return  an integer representing the integer value of {@code quantity}.
     * @throws InvMgrException if {@code quantity} could not be parsed into a valid non-negative integer.
     */
    public static int parseQuantity(String quantity) throws InvMgrException {
        String trimmedQuantity = quantity.trim();
        try {
            int value = Integer.parseInt(trimmedQuantity);
            if (value <= 0) {
                throw new InvMgrException(Messages.INVALID_QUANTITY);
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new InvMgrException(Messages.INVALID_QUANTITY);
        }
    }

    /**
     * Return a string representation of date as LocalDate type.
     *
     * @param dateStr Date entered by the user as String type.
     * @return Date as LocalDate type.
     * @throws InvMgrException if the given string representation of date does not follow YYYY-MM-DD.
     */
    public static LocalDate parseDate(String dateStr) throws InvMgrException {
        try {
            LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvMgrException(Messages.INVALID_DATE_FORMAT);
        }
    }
}
