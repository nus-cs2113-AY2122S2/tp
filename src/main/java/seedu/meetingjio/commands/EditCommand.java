package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Lesson;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import seedu.meetingjio.parser.ParserHelperMethods;

import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.exceptions.InvalidAttributeValueException;
import seedu.meetingjio.exceptions.InvalidModeException;
import seedu.meetingjio.exceptions.InvalidDayException;
import seedu.meetingjio.exceptions.InvalidTimeException;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_ATTRIBUTE_VALUE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;

import java.util.HashMap;
import java.util.Map;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    private static final String TITLE = "/t";
    private static final String DAY = "d/";
    private static final String START_TIME = "st/";
    private static final String END_TIME = "et/";
    private static final String MODE = "m/";

    private final String name;
    private final int index;
    private final Map<String, String> newValues;

    public EditCommand(String name, int index, Map<String, String> newValues) {
        this.name = name;
        this.index = index;
        this.newValues = newValues;
    }

    /**
     * Execute EditEvent command using the timetable provided.
     *
     * @attribute
     *masterTimetable MasterTimetable
     *
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        try {
            Timetable timetable = masterTimetable.getByName(name);
            Event event = timetable.get(index);
            Map<String, String> originalValues = getEventInfo(event);

            Boolean isValidInput = editEvent(event, newValues);
            if (!isValidInput) {
                editEvent(event, originalValues);
                return editAbort(ERROR_INVALID_ATTRIBUTE_VALUE);
            }
            if (timetable.isDuplicate(event)) {
                editEvent(event, originalValues);
                return editAbort(ERROR_DUPLICATE_EVENT);
            }
            if (timetable.isOverlap(event)) {
                editEvent(event, originalValues);
                return editAbort(ERROR_OVERLAPPING_EVENT);
            }
            return editConfirmation(event);

        } catch (TimetableNotFoundException tnfe) {
            return editAbort(ERROR_INVALID_USER);
        } catch (IndexOutOfBoundsException ioe) {
            return editAbort(ERROR_INDEX_OUT_OF_BOUND);
        }
    }

    private Boolean editEvent(Event event, Map<String, String> eventInfo) {
        for (Map.Entry<String, String> entry : eventInfo.entrySet()) {
            String attribute = entry.getKey();
            String value = eventInfo.get(attribute);
            try {
                editHelper(event, attribute, value);
            } catch (InvalidAttributeValueException iave) {
                return false;
            }
        }
        return true;
    }

    private void editHelper(Event event, String attribute, String value) throws InvalidAttributeValueException {
        switch (attribute) {
        case TITLE:
            event.setTitle(value);
            break;
        case MODE:
            try {
                ParserHelperMethods.checkMode(value);
                event.setMode(value);
            } catch (InvalidModeException ime) {
                throw new InvalidAttributeValueException();
            }
            break;
        case DAY:
            try {
                ParserHelperMethods.checkDay(value);
                event.setDay(value);
            } catch (InvalidDayException ide) {
                throw new InvalidAttributeValueException();
            }
            break;
        case START_TIME:
            try {
                int startTime = Integer.parseInt(value);
                int endTime = event.getEndTime();
                ParserHelperMethods.checkTime(startTime, endTime);
            } catch (InvalidTimeException | NumberFormatException ite) {
                throw new InvalidAttributeValueException();
            }
            break;
        case END_TIME:
            try {
                int endTime = Integer.parseInt(value);
                int startTime = event.getStartTime();
                ParserHelperMethods.checkTime(startTime, endTime);
            } catch (InvalidTimeException | NumberFormatException ite) {
                throw new InvalidAttributeValueException();
            }
            break;
        default:
            return;
        }
    }

    private Map<String, String> getEventInfo(Event event) {
        Map<String, String> eventInfo = new HashMap<>();
        String startTime = Integer.toString(event.getStartTime());
        String endTime = Integer.toString(event.getEndTime());
        eventInfo.put(TITLE, event.getTitle());
        eventInfo.put(DAY, event.getDay());
        eventInfo.put(START_TIME, startTime);
        eventInfo.put(END_TIME, endTime);
        eventInfo.put(MODE, event.getMode());
        return eventInfo;
    }

    /**
     * Inform user that event has been edited.
     *
     * @attribute
     *event Event that was edited
     */
    private String editConfirmation(Event event) {
        return String.format("The event has been updated to the following:\n%s", event);
    }

    /**
     * Inform user that edit is aborted.
     *
     * @attribute
     *event Event that user intends to edit
     */
    private String editAbort(String error) {
        return String.format("%s Edit aborted.", error);
    }
}
