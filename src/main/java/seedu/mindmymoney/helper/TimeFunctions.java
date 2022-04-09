package seedu.mindmymoney.helper;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.constants.ValidationRegexTypes;

import java.time.LocalDate;

import static seedu.mindmymoney.constants.Indexes.INDEX_OF_FIRST_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_SECOND_ITEM;
import static seedu.mindmymoney.constants.Indexes.INDEX_OF_THIRD_ITEM;

/**
 * Container for functions needed to calculate and format time.
 */
public class TimeFunctions {
    private static final int LEAP_YEAR_NUMBER = 4;

    /**
     * Checks if date input format is valid.
     *
     * @param input The string of the date input.
     * @return true if format is valid, false otherwise.
     */
    public static boolean isValidInput(String input) {
        if (input.matches(ValidationRegexTypes.VALIDATION_REGEX_D)
                || input.matches(ValidationRegexTypes.VALIDATION_REGEX_M)
                || input.matches(ValidationRegexTypes.VALIDATION_REGEX_Y)) {
            return true;
        }
        return false;

    }

    /**
     * Checks is parsed date is a valid date in the calendar.
     *
     * @param inputTime date that is parsed in.
     * @throws MindMyMoneyException throws an exception when the date parsed is in not in the calendar.
     */
    public static void checkValidDate(String inputTime) throws MindMyMoneyException {
        String[] date = inputTime.split("/");
        String day = date[INDEX_OF_FIRST_ITEM];
        int dayInInt = Integer.parseInt(day);
        String month = date[INDEX_OF_SECOND_ITEM];
        String year = date[INDEX_OF_THIRD_ITEM];
        int yearInInt = Integer.parseInt(year);
        if (!isValidInput(inputTime)) {
            throw new MindMyMoneyException("Date has to be valid and in this format \"dd/mm/yyyy\"");
        } else if (!(yearInInt % LEAP_YEAR_NUMBER == 0) && month.equals("02") && (dayInInt > 28)) {
            throw new MindMyMoneyException(day + "/" + month + " is not a valid dd/mm in a non leap year!");
        } else if ((yearInInt % LEAP_YEAR_NUMBER == 0) && month.equals("02") && (dayInInt > 29)) {
            throw new MindMyMoneyException(day + "/" + month + " is not a valid dd/mm in a leap year!");
        } else if ((month.equals("04") || month.equals("06") || month.equals("09") || month.equals("11"))
                && dayInInt > 30) {
            throw new MindMyMoneyException(day + "/" + month + " is not a valid dd/mm in this month!");
        }
    }

    /**
     * Checks if parsed date is after the current date.
     *
     * @param date date that is parsed in.
     * @throws MindMyMoneyException throws an exception when the date parsed is after current date.
     */
    public static void checkAfterCurrentDate(LocalDate date) throws MindMyMoneyException {
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate)) {
            throw new MindMyMoneyException("Please enter a valid date that is before today or today's date itself.");
        }
    }
}
