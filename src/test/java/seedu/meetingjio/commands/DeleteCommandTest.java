//package seedu.meetingjio.commands;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.meetingjio.timetables.Timetable;
//import seedu.meetingjio.events.Lesson;
//import seedu.meetingjio.exceptions.DuplicateEventException;
//import seedu.meetingjio.exceptions.OverlappingEventException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.meetingjio.common.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;
//
//public class DeleteCommandTest {
//
//    Timetable timetable = new Timetable();
//
//    /**
//     * Set up a timetable object to set up and test commands.
//     *
//     */
//    @BeforeEach
//    void setUp() throws DuplicateEventException, OverlappingEventException {
//
//    }
//
//    /**
//     * Test method to see if code catches instance of index out of bounds and if it throws exception.
//     *
//     */
//    @Test
//    public void deleteCommand_IndexOutOfBoundsSize_throwException() {
//        //delete index that is not found in list
//        DeleteCommand deleteIndexSize = new DeleteCommand(timetable.size());
//        deleteIndexSize.execute(timetable);
//        assertEquals(ERROR_INDEX_OUT_OF_BOUND, deleteIndexSize.execute(timetable));
//    }
//
//}
