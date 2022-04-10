package seedu.sherpass.util.parser;

import org.junit.jupiter.api.Test;
import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.UnmarkCommand;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.util.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.Message.WHITESPACE;
import static seedu.sherpass.util.parser.TaskParser.prepareTaskDateTime;

public class ParserTest {
    Ui ui = new Ui();

    @Test
    public void parseCommand_expectsAddCommand() throws InvalidInputException {
        String input = "add return book /do 12/4/2022 /start 12:00 /end 13:00";
        Command addCommand = Parser.parseCommand(input, ui);

        AddCommand expectedAddCommand = new AddCommand("return book",
                prepareTaskDateTime("12/4/2022" + WHITESPACE, "12:00", inputWithTimeFormat),
                prepareTaskDateTime("12/4/2022" + WHITESPACE, "13:00", inputWithTimeFormat));
        assertEquals(addCommand, expectedAddCommand);
    }

    @Test
    public void parseCommand_expectsShowCommand() {
        String input = "show today";
        Command showCommand = Parser.parseCommand(input, ui);

        assertEquals(showCommand, new ShowCommand(null, "today"));
    }

    @Test
    public void parseCommand_expectsExitCommand() {
        String input = "bye";
        Command exitCommand = Parser.parseCommand(input, ui);

        assertTrue(exitCommand instanceof ExitCommand);
    }

    @Test
    public void parseCommand_expectsMarkCommand() {
        String input = "mark 1";
        Command markCommand = Parser.parseCommand(input, ui);

        assertTrue(markCommand instanceof MarkCommand);
    }

    @Test
    public void parseCommand_expectsUnmarkCommand() {
        String input = "unmark 1";
        Command unmarkCommand = Parser.parseCommand(input, ui);

        assertTrue(unmarkCommand instanceof UnmarkCommand);
    }

    @Test
    public void parseCommand_expectsStudyCommand() {
        String input = "study";
        Command studyCommand = Parser.parseCommand(input, ui);

        assertTrue(studyCommand instanceof StudyCommand);
    }

    @Test
    public void parseCommand_expectsHelpCommand() {
        String input = "help";
        Command helpCommand = Parser.parseCommand(input, ui);

        assertTrue(helpCommand instanceof HelpCommand);
    }
}
