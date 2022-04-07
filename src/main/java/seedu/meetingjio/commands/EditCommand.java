//@@author yanjie1017

package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;
import seedu.meetingjio.parser.ParserHelperMethods;

import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.exceptions.InvalidAttributeValueException;
import seedu.meetingjio.exceptions.InvalidModeException;
import seedu.meetingjio.exceptions.InvalidDayException;
import seedu.meetingjio.exceptions.InvalidTimeException;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_ATTRIBUTE_VALUE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_INDEX;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EDIT_MEETING;

import java.util.HashMap;
import java.util.Map;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    private static final String TITLE = "t";
    private static final String DAY = "d";
    private static final String START_TIME = "st";
    private static final String END_TIME = "et";
    private static final String MODE = "m";

    private final String name;
    private final int index;
    private final Map<String, String> newValues;

    public EditCommand(String name, int index, Map<String, String> newValues) {
        this.name = name;
        this.index = index;
        this.newValues = newValues;
    }

    /**
     * Edit lesson based on the user input if the input values are valid.
     * Otherwise, abort edit.
     *
     * @param masterTimetable The collection of everyone's timetable
     * @return String Edit confirmation showing that the lesson is edited
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        try {
            Timetable timetable = masterTimetable.getByName(name);
            Event event = timetable.get(index - 1);

            if (event instanceof Meeting) {
                return editAbort(ERROR_EDIT_MEETING);
            }

            Map<String, String> originalValues = getEventInfo(event);
            Boolean isValidInput = editEvent(event, newValues);
            if (!isValidInput) {
                editEvent(event, originalValues);
                return editAbort(ERROR_INVALID_ATTRIBUTE_VALUE);
            }
            if (timetable.isValid()) {
                editEvent(event, originalValues);
                return editAbort(ERROR_OVERLAPPING_EVENT);
            }
            return editConfirmation(event);

        } catch (TimetableNotFoundException tnfe) {
            return editAbort(ERROR_INVALID_USER);
        } catch (IndexOutOfBoundsException ioe) {
            return editAbort(ERROR_INVALID_INDEX);
        }
    }

    private Boolean editEvent(Event event, Map<String, String> eventInfo) {
        try {
            for (Map.Entry<String, String> entry : eventInfo.entrySet()) {
                String attribute = entry.getKey();
                String value = eventInfo.get(attribute);
                editAttribute(event, attribute, value);
            }
            editTimes(event, eventInfo);
            return true;
        } catch (InvalidAttributeValueException iave) {
            return false;
        }
    }

    private void editAttribute(Event event, String attribute, String value) throws InvalidAttributeValueException {
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
        default:
            return;    
        }
    }

    private void editTimes(Event event, Map<String, String> eventInfo) throws InvalidAttributeValueException {
        try {
            if (!eventInfo.containsKey(START_TIME) && !eventInfo.containsKey(END_TIME)) {
                return;
            }

            int startTime = event.getStartTime();
            int endTime = event.getEndTime();
            if (eventInfo.containsKey(START_TIME)) {
                String newStartTime = eventInfo.get(START_TIME);
                startTime = Integer.parseInt(newStartTime);
            }
            if (eventInfo.containsKey(END_TIME)) {
                String newEndTime = eventInfo.get(END_TIME);
                endTime = Integer.parseInt(newEndTime);
            }

            ParserHelperMethods.checkTime(startTime, endTime);
            event.setStartTime(startTime);
            event.setEndTime(endTime);
        } catch (InvalidTimeException | NumberFormatException ite) {
            throw new InvalidAttributeValueException();
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
     * @param event Event that has been edited
     * @return String confirmation of event being edited
     */
    private String editConfirmation(Event event) {
        return String.format("The lesson has been updated to the following:\n%s", event);
    }

    /**
     * Inform user that edit is aborted.
     *
     * @param error Error messages
     * @return String confirmation of event not being edited
     */
    private String editAbort(String error) {
        return String.format("%s Edit aborted.", error);
    }
}
