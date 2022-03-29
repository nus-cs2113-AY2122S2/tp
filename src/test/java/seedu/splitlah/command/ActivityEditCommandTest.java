package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivityEditCommandTest {

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
        String activityOneArgs = "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
        Command createActivityOne = Parser.getCommand(activityOneArgs);
        createActivityOne.run(manager);
    }

    @Test
    public void run_editActivityName_nameIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n editedLunch /p Alice /i Alice Bob Charlie /co 15";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        System.out.println(manager.getProfile().getSession(1).getActivity(1).getActivityName());
        assertEquals("editedLunch", manager.getProfile().getSession(1).getActivity(1).getActivityName());
    }

    @Test
    public void run_editActivityPayer_payerIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n Lunch /p Bob /i Alice Bob Charlie /co 15";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        assertEquals("Bob", manager.getProfile().getSession(1).getActivity(1).getPersonPaid().getName());
    }

    @Test
    public void run_editActivityPersonList_PersonListIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n Lunch /p Alice /i Alice Bob /co 15";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        ArrayList<Person> involvedPersonList =
                manager.getProfile().getSession(1).getActivity(1).getInvolvedPersonList();
        assertEquals(2, involvedPersonList.size());
        assertEquals("Alice", involvedPersonList.get(0).getName());
        assertEquals("Bob", involvedPersonList.get(1).getName());
    }

    @Test
    public void run_editActivityTotalCost_totalCostIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 20";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        assertEquals(20, manager.getProfile().getSession(1).getActivity(1).getTotalCost());
    }

    @Test
    public void run_editActivityGst_gstIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 20 /gst 7";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        double newCost = manager.getProfile().getSession(1).getActivity(1).getTotalCost();
        assertTrue(Math.abs(newCost - 21.4) < 0.1);
    }

    @Test
    public void run_editActivityServiceCharge_serviceChargeIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 20 /sc 10";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        double newCost = manager.getProfile().getSession(1).getActivity(1).getTotalCost();
        assertTrue(Math.abs(newCost - 22) < 0.1);
    }

    @Test
    public void run_editActivityCostList_costListIsEdited() throws InvalidDataException {
        Activity activity = manager.getProfile().getSession(1).getActivity(1);
        String userInput = "activity /edit /sid 1 /aid 1 /n Lunch /p Alice /i Alice Bob Charlie /cl 5 10 15";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        ArrayList<Person> involvedPersonList = activity.getInvolvedPersonList();
        double aliceCost = activity.getInvolvedPersonList().get(0).getActivityCostList().get(0).getCostOwed();
        double bobCost = activity.getInvolvedPersonList().get(1).getActivityCostList().get(0).getCostOwed();
        double charlieCost = activity.getInvolvedPersonList().get(2).getActivityCostList().get(0).getCostOwed();
        assertEquals(10, bobCost);
        assertEquals(15, charlieCost);
    }

    @Test
    public void run_editWholeActivity_wholeActivityIsEdited() throws InvalidDataException {
        String userInput = "activity /edit /sid 1 /aid 1 /n editedLunch /p Bob /i Alice Bob /co 20 /gst 7 /sc 10";
        Command command = Parser.getCommand(userInput);
        assertEquals(ActivityEditCommand.class, command.getClass());
        command.run(manager);
        Activity editedActivity = manager.getProfile().getSession(1).getActivity(1);
        assertEquals(1, editedActivity.getActivityId());
        assertEquals(1, manager.getProfile().getSession(1).getSessionId());
        assertEquals("editedLunch", editedActivity.getActivityName());
        assertEquals("Bob", editedActivity.getPersonPaid().getName());
        assertEquals(2, editedActivity.getInvolvedPersonList().size());
        double newCost = editedActivity.getTotalCost();
        assertTrue(newCost - (20 * 1.07 * 1.1) < 0.001);
    }

    /**
     * Checks if an activity is not edited when an activity has duplicate names in the involved list.
     */
    @Test
    public void run_hasNameDuplicatesInInvolvedList_activityListSizeRemainsOne() {
        String userInput = "activity /edit /sid 1 /n Dinner /p Alice /i Alice Alice Charlie /co 30";
        Command command = Parser.getCommand(userInput);
        assertEquals(InvalidCommand.class, command.getClass());
    }
}
