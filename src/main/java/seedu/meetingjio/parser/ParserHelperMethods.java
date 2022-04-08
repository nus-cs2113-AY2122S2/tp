//@@author angyongming

package seedu.meetingjio.parser;

import seedu.meetingjio.exceptions.InvalidNameException;
import seedu.meetingjio.exceptions.InvalidDayException;
import seedu.meetingjio.exceptions.InvalidModeException;
import seedu.meetingjio.exceptions.InvalidTimeException;
import seedu.meetingjio.exceptions.MissingValueException;

public class ParserHelperMethods {

    /**
     * Checks the validity of the user's given startTime and endTime.
     *
     * @param startTime Time at which the event begins
     * @param endTime   Time at which the event ends
     * @throws InvalidTimeException If the given hours and minutes are invalid, or if startTime is later than endTime
     */
    public static void checkTime(int startTime, int endTime) throws InvalidTimeException {
        int startMinutes = startTime % 100;
        int endMinutes = endTime % 100;
        boolean invalidMinutes = startMinutes >= 60 || endMinutes >= 60;
        int startHours = startTime / 100;
        int endHours = endTime / 100;
        boolean invalidHours = startHours >= 24 || endHours >= 24;
        if (invalidMinutes || invalidHours || startTime >= endTime) {
            throw new InvalidTimeException();
        }
    }

    /**
     * Checks that all parameters have a non-null value.
     *
     * @param eventDescription Array of user's input
     * @throws MissingValueException If at least one parameter has no value
     */
    public static  void checkNonNullValues(String[] eventDescription) throws MissingValueException {
        for (int i = 0; i < eventDescription.length; i++) {
            if (eventDescription[i].length() == 0) {
                throw new MissingValueException();
            }
            assert (eventDescription[i].length() != 0) : "The parameters have non-null values";
        }
    }

    /**
     * Checks that the mode given by the user is either online or physical.
     *
     * @param mode String given by user
     * @throws InvalidModeException If mode is neither online nor physical
     */
    public static void checkMode(String mode) throws InvalidModeException {
        if (mode.equals("online") || mode.equals("physical")) {
            return;
        }
        throw new InvalidModeException();
    }

    /**
     * Ensures that the 'day' parameter in user's input is a valid day.
     *
     * @param day String given by user
     * @throws InvalidDayException If value of 'day' does not correspond to an actual day
     */
    public static void checkDay(String day) throws InvalidDayException {
        switch (day) {
        case "monday":
        case "tuesday":
        case "wednesday":
        case "thursday":
        case "friday":
        case "saturday":
        case "sunday":
            break;
        default:
            throw new InvalidDayException();
        }
    }

    /**
     * Ensures that 'name' parameter in user's input does not contain special character
     * and not equal to 'all'.
     *
     * @param name String given by user
     * @throws InvalidNameException If input contains special character or is 'all'
     */
    public static void checkName(String name) throws InvalidNameException {
        if (name.equals("all")) {
            throw new InvalidNameException();
        }
        for (int i = 0; i < name.length(); i++)  {
            char x = name.charAt(i);
            if (x >= 'A' && x <= 'Z') {
                continue;
            }
            if (x >= 'a' && x <= 'z') {
                continue;
            }
            if (x >= '0' && x <= '9') {
                continue;
            }
            throw new InvalidNameException();
        }
    }

}
