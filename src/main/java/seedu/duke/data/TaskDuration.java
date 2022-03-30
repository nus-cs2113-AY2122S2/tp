package seedu.duke.data;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.WrongDurationFormatException;
import seedu.duke.util.NumberConstants;
import seedu.duke.util.StringConstants;


public class TaskDuration {

    private static final long MINUTE_PER_HOUR = NumberConstants.MINUTE_PER_HOUR;
    private static final long MAXIMUM_ALLOWED_DURATION_NUMBER = NumberConstants.MAXIMUM_ALLOWED_DURATION_NUMBER;
    private static final String DURATION_GROUP_WORD = StringConstants.DURATION_GROUP_WORD;
    private static final String DURATION_UNIT_GROUP_WORD = StringConstants.DURATION_UNIT_GROUP_WORD;
    private static final String TO_STRING_FORMAT_WITH_HOUR_AND_MINUTE =
            StringConstants.TO_STRING_FORMAT_WITH_HOUR_AND_MINUTE;
    private static final String TO_STRING_FORMAT_WITH_HOUR_ONLY = StringConstants.TO_STRING_FORMAT_WITH_HOUR_ONLY;
    private static final String TO_STRING_FORMAT_WITH_MINUTE_ONLY = StringConstants.TO_STRING_FORMAT_WITH_MINUTE_ONLY;
    private static final String DURATION_STRING_FORMAT = StringConstants.DURATION_STRING_FORMAT;
    private static final String[] HOUR_UNIT_WORD = {"h", "H", "hours", "Hours", "hour", "Hour"};
    private static final String[] MINUTE_UNIT_WORD = {"m", "M", "min", "Min", "minutes", "Minutes", "minute", "Minute"};


    protected Duration taskDuration;

    /**
     * A duration of a task.
     * @param durationString The duration, as a string.
     * @throws ModHappyException If the duration could not be properly parsed.
     */
    public TaskDuration(String durationString) throws ModHappyException {
        HashMap<String, String> parsedDurationString = parseDurationString(durationString);

        // the input unit is hours
        if (Arrays.asList(HOUR_UNIT_WORD).contains(parsedDurationString.get(DURATION_UNIT_GROUP_WORD))) {
            double numberOfHoursDouble = Double.parseDouble(parsedDurationString.get(DURATION_GROUP_WORD));
            if (numberOfHoursDouble > MAXIMUM_ALLOWED_DURATION_NUMBER) {
                // Exceeds upperbound
                throw new WrongDurationFormatException();
            }
            long numberOfHoursInt = (long) numberOfHoursDouble;
            taskDuration = Duration.ofHours(numberOfHoursInt);
            long offSetMinute = Math.round(((numberOfHoursDouble - numberOfHoursInt) * MINUTE_PER_HOUR));
            taskDuration = taskDuration.plusMinutes(offSetMinute);
            return;
        }

        // the input unit is minutes
        if (Arrays.asList(MINUTE_UNIT_WORD).contains(parsedDurationString.get(DURATION_UNIT_GROUP_WORD))) {
            double numberOfMinutesDouble = Double.parseDouble(parsedDurationString.get(DURATION_GROUP_WORD));
            if (numberOfMinutesDouble > MAXIMUM_ALLOWED_DURATION_NUMBER) {
                // Exceeds upperbound
                throw new WrongDurationFormatException();
            }
            taskDuration = Duration.ofMinutes(
                    Math.round(numberOfMinutesDouble));
            return;
        }

        // no legal input unit match, throw exception
        throw new WrongDurationFormatException();
    }


    // Intentionally implement the parse method independently here, because later will refactor the command parser.
    private HashMap<String, String> parseDurationString(String durationString) throws ModHappyException {
        Pattern commandPattern = Pattern.compile(DURATION_STRING_FORMAT);
        Matcher matcher = commandPattern.matcher(durationString.trim());
        HashMap<String, String> parserDurationString = new HashMap<>();
        if (!matcher.matches()) {
            throw new WrongDurationFormatException();
        }
        try {
            parserDurationString.put(DURATION_UNIT_GROUP_WORD, matcher.group(DURATION_UNIT_GROUP_WORD).trim());
            parserDurationString.put(DURATION_GROUP_WORD, matcher.group(DURATION_GROUP_WORD).trim());
        } catch (Exception e) {
            throw new WrongDurationFormatException();
        }
        return parserDurationString;
    }


    @Override
    public String toString() {
        long numberOfHours = taskDuration.toHours();
        long numberOfMinutes = taskDuration.toMinutes();

        if (numberOfHours == 0) {
            // The duration is less than 1 hour
            return String.format(TO_STRING_FORMAT_WITH_MINUTE_ONLY, taskDuration.toMinutes());
        } else if (numberOfHours * MINUTE_PER_HOUR < numberOfMinutes) {
            // The duration is more than 1 hour and has minute offset
            long minuteOffset = numberOfMinutes - numberOfHours * MINUTE_PER_HOUR;
            return String.format(TO_STRING_FORMAT_WITH_HOUR_AND_MINUTE, taskDuration.toHours(), minuteOffset);
        } else {
            // The duration is more than 1 hour but with no minute offset
            return String.format(TO_STRING_FORMAT_WITH_HOUR_ONLY, taskDuration.toHours());
        }

    }
}
