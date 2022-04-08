//@@author yanjie1017

package seedu.meetingjio.commands;

import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.timetables.MasterTimetable;

import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;

public class AddLessonCommand extends Command {
    public static final String COMMAND_WORD = "add_lesson";

    private final String name;
    private final String title;
    private final String day;
    private final int startTime;
    private final int endTime;
    private final String mode;

    public AddLessonCommand(String name, String title, String day, int startTime, int endTime, String mode) {
        this.name = name;
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
    }

    /**
     * Add lesson to the user's timetable.
     *
     * @param masterTimetable The collection of everyone's timetable
     * @return String Add confirmation showing that the lesson is added
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        try {
            Lesson lesson = new Lesson(title, day, startTime, endTime, mode);
            masterTimetable.addLesson(lesson, name);
            return addConfirmation(lesson, name);
        } catch (TimetableNotFoundException tnfe) {
            return ERROR_INVALID_USER;
        } catch (DuplicateEventException dee) {
            return ERROR_DUPLICATE_EVENT;
        } catch (OverlappingEventException oee) {
            return ERROR_OVERLAPPING_EVENT;
        }
    }

    /**
     * Inform user that lesson has been added.
     *
     * @param lesson Lesson that was added
     * @param name User
     * @return String
     */
    private String addConfirmation(Lesson lesson, String name) {
        return String.format("The following event has been added to %s's timetable:\n%s",
                name, lesson);
    }
}
