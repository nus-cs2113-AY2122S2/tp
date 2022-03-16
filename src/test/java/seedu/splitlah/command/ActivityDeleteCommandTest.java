package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDeleteCommandTest {

    Manager manager = new Manager();

    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);
        String activityOneArgs = "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        Command createActivityOne = Parser.getCommand(activityOneArgs);
        createActivityOne.run(manager);
    }

}
