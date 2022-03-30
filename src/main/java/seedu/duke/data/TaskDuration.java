package seedu.duke.data;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.exceptions.WrongDurationFormatException;


public class TaskDuration {


    private static final String DURATION_STRING_FORMAT = "(?<duration>[1-9]\\d*\\.?\\d*|0\\.\\d*[1-9])"
            + "\\s*(?<durationUnit>\\bm|M|min|Min|minutes|Minutes|minute|Minute"
            + "|h|H|hours|Hours|hour|Hour|\\b|"
            + "\\Bm|M|min|Min|minutes|Minutes|minute|Minute|h|H|hours|Hours|hour|Hour\\b)";
    private static final String[] HOUR_UNIT_WORD = {"h", "H", "hours", "Hours", "hour", "Hour"};
    private static final String[] MINUTE_UNIT_WORD = {"m", "M", "min", "Min", "minutes", "Minutes", "minute", "Minute"};
    private static final String DURATION_GROUP_WORD = "duration";
    private static final String DURATION_UNIT_GROUP_WORD = "durationUnit";
    private static final String TO_STRING_FORMAT_WITH_HOUR_AND_MINUTE = "%d hours %d minutes";
    private static final String TO_STRING_FORMAT_WITH_HOUR_ONLY = "%d hours";
    private static final String TO_STRING_FORMAT_WITH_MINUTE_ONLY = "%d minutes";
    private static final long MINUTE_PER_HOUR = 60;
    protected Duration taskDuration;

    /**
     * A duration of a task.
     * @param durationString String-format duration
     * @throws ModHappyException Fails to create the TaskDuration.
     */
    public TaskDuration(String durationString) throws ModHappyException {


        HashMap<String, String> parsedDurationString = parseDurationString(durationString);

        // the input unit is hours
        if (Arrays.asList(HOUR_UNIT_WORD).contains(parsedDurationString.get(DURATION_UNIT_GROUP_WORD))) {
            double numberOfHoursDouble = Double.parseDouble(parsedDurationString.get(DURATION_GROUP_WORD));
            long numberOfHoursInt = (long) numberOfHoursDouble;
            taskDuration = Duration.ofHours(numberOfHoursInt);
            long offSetMinute = Math.round(((numberOfHoursDouble - numberOfHoursInt) * MINUTE_PER_HOUR));
            taskDuration = taskDuration.plusMinutes(offSetMinute);
            return;
        }

        // the input unit is minutes
        if (Arrays.asList(MINUTE_UNIT_WORD).contains(parsedDurationString.get(DURATION_UNIT_GROUP_WORD))) {
            taskDuration = Duration.ofMinutes(
                    Math.round(Double.parseDouble(parsedDurationString.get(DURATION_GROUP_WORD))));
            return;
        }

        // no legal input unit match, throw exception
        throw new WrongDurationFormatException();

    }


    // Intentionally implement the parse method independently here, because later will refact the command parser.
    private HashMap<String, String> parseDurationString(String durationString) throws ModHappyException {
        Pattern commandPattern = Pattern.compile(DURATION_STRING_FORMAT);
        Matcher matcher = commandPattern.matcher(durationString.trim());
        HashMap<String, String> parserDurationString = new HashMap<>();
        if (!matcher.matches()) {
            throw new ParseException();
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
