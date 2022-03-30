package seedu.duke.parser;

import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

public class ParserUtils {
    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws InvMgrException if the specified index is invalid (valid integers are non-zero unsigned integer).
     */
    public static int parseIndex(String oneBasedIndex) throws InvMgrException {
        String trimmedIndex = oneBasedIndex.trim();
        try {
            int value = Integer.parseInt(trimmedIndex);
            if (!(value > 0)) {
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
     * @throws InvMgrException if the given {@code quantity} is invalid (valid integers are non-negative integers).
     */
    public static int parseQuantity(String quantity) throws InvMgrException {
        String trimmedQuantity = quantity.trim();
        try {
            int value = Integer.parseInt(trimmedQuantity);
            if (!(value >= 0)) {
                throw new InvMgrException(Messages.INVALID_QUANTITY);
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new InvMgrException(Messages.INVALID_QUANTITY);
        }
    }

    /**
     * Returns -1 if {@code relative} is "-", and 1 if {@code relative} is "+" 1.
     * The value returned represents a multiplier on the quantity, used to edit quantity relatively.
     * @throws InvMgrException if the given {@code relative} is invalid (not either of the two options)
     */
    public static int parseRelative(String relative) throws InvMgrException {
        String trimmedRelative = relative.trim();
        if (trimmedRelative.equals("+")) {
            return 1;
        } else if (trimmedRelative.equals("-")) {
            return -1;
        }
        throw new InvMgrException(Messages.INVALID_RELATIVE_MESSAGE);
    }
}
