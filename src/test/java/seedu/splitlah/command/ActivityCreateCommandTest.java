package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityCreateCommandTest {

    Manager manager = new Manager();

    /**
     * Creates a session that is stored and managed by the Manager object.
     * Creates an activity in the new session that was created.
     */
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob Charlie";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);
        String activityOneArgs = "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /c 15";
        Command createActivityOne = Parser.getCommand(activityOneArgs);
        createActivityOne.run(manager);
    }

    /**
     * Checks if an activity is created when an activity is missing both cost and cost list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_hasMissingCostAndCostList_activityListSizeRemainsOne() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

    /**
     * Checks if an activity is created when an activity has both cost and cost list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_hasBothCostAndCostList_activityListSizeRemainsOne() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /c 30 /cl 10 10 10";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

    /**
     * Checks if an activity is created when an activity has different length for involved list and cost list.
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_costListAndInvolvedListDifferentLength_activityListSizeRemainsOne() throws InvalidDataException {
        String firstUserInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /cl 10 10";
        Command firstCommand = Parser.getCommand(firstUserInput);
        firstCommand.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
        String secondUserInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob /cl 10 10 10";
        Command secondCommand = Parser.getCommand(secondUserInput);
        secondCommand.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

    @Test
    public void run_hasNameDuplicatesInInvolvedList_activityListSizeRemainsOne() throws InvalidDataException {
        String userInput = "activity /create /sid 1 /n Dinner /p Alice /i Alice Alice Charlie /c 30";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(1, manager.getProfile().getSession(1).getActivityList().size());
    }

}
