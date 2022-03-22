package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupViewCommandTest {

    Manager manager = new Manager();

    @BeforeEach
    void setUp() {
        String groupArgs = "group /view /gid 1";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);
    }
}