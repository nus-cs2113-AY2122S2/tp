package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.duke.common.ErrorMessages.ERROR_INVALID_DAY;
import static seedu.duke.common.ErrorMessages.ERROR_INVALID_TIME;
import static seedu.duke.common.ErrorMessages.ERROR_INVALID_MODE;
import static seedu.duke.common.ErrorMessages.ERROR_MISSING_PARAMETERS;
import static seedu.duke.common.ErrorMessages.ERROR_MISSING_VALUES;

public class ParserTest {
    
    Timetable timetable = new Timetable();

    @Test
    public void prepareAdd_invalidDay_throwException() {
        String inputString = "add n/john l/cs2113 d/mon st/1600 et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_DAY, command.execute(timetable));
    }

    @Test
    public void prepareAdd_timeOutOfRange_throwException() {
        String inputString = "add n/john l/cs2113 d/monday st/1690 et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(timetable));
    }

    @Test
    public void prepareAdd_startTimeGreaterThanEndTime_throwException() {
        String inputString = "add n/john l/cs2113 d/monday st/1800 et/1600 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(timetable));
    }

    @Test
    public void prepareAdd_timeNotInteger_throwException() {
        String inputString = "add n/john l/cs2113 d/mon st/noon et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_TIME, command.execute(timetable));
    }

    @Test
    public void prepareAdd_invalidMode_throwException() {
        String inputString = "add n/john l/cs2113 d/monday st/1600 et/1800 m/lecture";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_INVALID_MODE, command.execute(timetable));
    }

    @Test
    public void prepareAdd_missingParameters_throwException() {
        String inputString = "add n/john l/cs2113 d/monday st/1600 et/1800";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_PARAMETERS, command.execute(timetable));
    }

    @Test
    public void prepareAdd_parametersMissingValues_throwException() {
        String inputString = "add n/john l/cs2113 d/ st/1600 et/1800 m/online";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(ERROR_MISSING_VALUES, command.execute(timetable));
    }

    @Test
    public void prepareDeleteCommand_invalidIndex_throwException() {
        Timetable timetable = new Timetable();
        String inputString = "delete hello";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(HelpCommand.class,command.getClass());
    }

    @Test
    public void prepareDeleteCommand_noIndex_throwException() {
        String inputString = "delete";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(HelpCommand.class,command.getClass());
    }
}
