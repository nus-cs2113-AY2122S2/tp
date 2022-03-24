package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupViewCommandTest {

    Manager manager = new Manager();

    /**
     * Creates a group that is stored and managed by the Profile object.
     */
    @BeforeEach
    void setUp() {
        String groupArgs = "group /create /n OP2 /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);
    }

    /**
     * Checks if an InvalidCommand object is returned when the Group Id delimiter is not provided by the user.
     */
    @Test
    void prepare_MissingGidDelimiter_InvalidCommand() {
        String argMissingGidDelimiter = "group /view 1";
        Command groupWithMissingGidDelimiter = Parser.getCommand(argMissingGidDelimiter);
        assertEquals(InvalidCommand.class, groupWithMissingGidDelimiter.getClass());
    }

    /**
     * Checks if an InvalidCommand object is returned when the Group Id delimiter is provided but no arguments
     * following the Group Id delimiter are provided by the user.
     */
    @Test
    void prepare_MissingGidArgument_InvalidCommand() {
        String argMissingGidArgument = "group /view /gid";
        Command groupWithMissingGidArgument = Parser.getCommand(argMissingGidArgument);
        assertEquals(InvalidCommand.class, groupWithMissingGidArgument.getClass());
    }

    /**
     * Checks if a GroupViewCommand object is returned when the Group Id delimiter and Group Id argument
     * are provided by the user.
     */
    @Test
    void prepare_validCommand_GroupViewCommand() {
        String groupArgs = "group /view /gid 1";
        Command viewGroup = Parser.getCommand(groupArgs);
        assertEquals(GroupViewCommand.class, viewGroup.getClass());
    }
}
