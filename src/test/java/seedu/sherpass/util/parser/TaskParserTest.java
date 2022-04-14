package seedu.sherpass.util.parser;

import org.junit.jupiter.api.Test;
import seedu.sherpass.command.AddCommand;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.util.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_NO_VALUE_FOR_PARAMETER_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_PREFIX;
import static seedu.sherpass.constant.Message.WHITESPACE;

public class TaskParserTest {

    @Test
    void parseArgument_byParameter_expectDateTime() {
        String date = "foo /by 21/12/2022 /start 09:00";
        String expected = "21/12/2022";
        String result = "";
        try {
            result = TaskParser.parseArgument("/by", date);
        } catch (InvalidInputException exception) {
            exception.printStackTrace();
        }
        assertEquals(result, expected);
    }

    @Test
    void parseArgument_invalidParameter_expectEmptyString() {
        String date = "foo /by 21/12/2022 09:00";
        String actualOutput = "";
        try {
            actualOutput = TaskParser.parseArgument("/do", date);
        } catch (InvalidInputException exception) {
            exception.printStackTrace();
        }
        assertEquals(EMPTY_STRING, actualOutput);
    }


    @Test
    void parseDescription_validDescription_expectFoo() {
        String input = "foo /bydate 21/12/2022";
        String expected = "foo";
        String result = TaskParser.parseDescription(input);
        assertEquals(expected, result);
    }

    @Test
    void parseDescription_emptyDescription_expectEmptyString() {
        String input = "/bydate 21/12/2022";
        String actualOutput = TaskParser.parseDescription(input);
        assertEquals(EMPTY_STRING, actualOutput);
    }

    @Test
    void prepareTaskDate_notDateInput_InvalidInputExceptionThrown() {
        String date = "foo";
        String time = "bar";
        assertThrows(InvalidInputException.class,
            () -> TaskParser.prepareTaskDateTime(date, time, inputWithTimeFormat));
    }

    @Test
    void prepareTaskDate_invalidDateFormat_InvalidInputExceptionThrown() {
        String date = "25-3-2022";
        String time = "13:00";
        assertThrows(InvalidInputException.class,
            () -> TaskParser.prepareTaskDateTime(date, time, inputWithTimeFormat));
    }

    @Test
    void prepareTaskDate_invalidTimeFormat_InvalidInputExceptionThrown() {
        String date = "25/3/2022";
        String time = "1300";
        assertThrows(InvalidInputException.class,
            () -> TaskParser.prepareTaskDateTime(date + WHITESPACE, time, inputWithTimeFormat));
    }

    @Test
    void prepareTaskDate_validInput_expectDateTime() throws InvalidInputException {
        String date = "25/3/2022";
        String time = "13:00";
        LocalDateTime actualOutput = TaskParser.prepareTaskDateTime(date + WHITESPACE, time, inputWithTimeFormat);
        LocalDateTime expectedOutput = LocalDateTime.of(2022, Month.MARCH, 25, 13, 0);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void prepareAdd_validInputWithoutByDateNoRepeat_expectAddCommandNotNull() {
        Ui ui = new Ui();
        String input = "add foo /do 26/3/2022 /start 14:00 /end 16:00";
        AddCommand testCommand = (AddCommand) TaskParser.prepareAdd(input, ui);
        assertNotNull(testCommand);
    }

    @Test
    void prepareAdd_validInputWithByDateNoRepeat_expectAddCommandNotNull() {
        Ui ui = new Ui();
        String input = "add foo /do 26/3/2022 /start 14:00 /end 16:00 /by 31/3/2022";
        AddCommand testCommand = (AddCommand) TaskParser.prepareAdd(input, ui);
        assertNotNull(testCommand);
    }

    @Test
    void prepareAdd_validInputWithoutByDateWithRepeat_expectAddCommandNotNull() {
        Ui ui = new Ui();
        String input = "add foo /do 26/3/2022 /start 14:00 /end 15:00 /repeat daily";
        AddCommand testCommand = (AddCommand) TaskParser.prepareAdd(input, ui);
        assertNotNull(testCommand);
    }

    @Test
    void prepareAdd_validInputWithoutByDateWithInvalidRepeat_expectErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        String input = "add foo /do 26/3/2022 /start 14:00 /end 16:00 /repeat";
        TaskParser.prepareAdd(input, ui);
        assertEquals(ERROR_PREFIX + ERROR_NO_VALUE_FOR_PARAMETER_MESSAGE
                + System.lineSeparator() + ui.getRepeatedCharacters("_", 60)
                + System.lineSeparator(), outContent.toString());
    }

    @Test
    void prepareAdd_validInputWithoutByDateWithBlankRepeat_expectErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        String input = "add foo /do 26/3/2022 /start 14:00 /end 16:00 /repeat  ";
        TaskParser.prepareAdd(input, ui);
        assertEquals(ERROR_PREFIX + ERROR_INVALID_FREQUENCY_MESSAGE
                + System.lineSeparator() + ui.getRepeatedCharacters("_", 60)
                + System.lineSeparator(), outContent.toString());
    }
}
