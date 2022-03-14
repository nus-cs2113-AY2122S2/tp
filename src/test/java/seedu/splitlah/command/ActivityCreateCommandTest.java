package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import seedu.splitlah.data.Manager;

class ActivityCreateCommandTest {

    Manager manager = new Manager();

    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob Charlie";
        Command createSessionOne = SessionCreateCommand.prepare(sessionOneArgs);
        createSessionOne.run(manager);
        String activityOneArgs = "activity /create /n Lunch /p Alice /i Alice Bob Charlie /c 15";
        Command createActivityOne = ActivityCreateCommand.prepare(activityOneArgs);
        createActivityOne.run(manager);
    }

}
