package seedu.splitlah.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SessionTest {

    Manager manager = new Manager();
    Session session;

    private static final int TEST_SESSION = 1;
    private static final String CREATE_TEST_SESSION_INPUT =
            "session /create /n Class outing /d 15-02-2022 /pl Alice Bob Charlie";
    private static final String CREATE_TEST_ACTIVITY_INPUT_ONE =
            "activity /create /sid 1 /n Lunch /p Alice /i Alice Bob Charlie /co 15";
    private static final String CREATE_TEST_ACTIVITY_INPUT_TWO =
            "activity /create /sid 1 /n Dinner /p Alice /i Alice Bob Charlie /co 30";

    /**
     * Creates a session that is stored and managed by the Manager object.
     */
    @BeforeEach
    void setUp() {
        Command createSessionCommand = Parser.getCommand(CREATE_TEST_SESSION_INPUT);
        createSessionCommand.run(manager);

        try {
            session = manager.getProfile().getSession(TEST_SESSION);
        } catch (InvalidDataException exception) {
            fail();
        }
    }

    // getActivity()

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when the getActivity method is
     * called and no Activity objects exists in the Session object.
     */
    @Test
    void getActivity_noActivityExists_InvalidDataExceptionThrown() {
        try {
            Activity activity = session.getActivity(1);
            fail();
        } catch (InvalidDataException exception) {
            assertEquals(Message.ERROR_SESSION_EMPTY_ACTIVITY_LIST, exception.getMessage());
        }
    }

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when the getActivity method is
     * called and no Activity objects with the specified activity unique identifier exists in the Session object.
     */
    @Test
    void getActivity_activityWithSpecifiedIdDoesNotExist_InvalidDataExceptionThrown() {
        Command createActivityCommand = Parser.getCommand(CREATE_TEST_ACTIVITY_INPUT_ONE);
        createActivityCommand.run(manager);

        try {
            Activity activity = session.getActivity(2);
            fail();
        } catch (InvalidDataException exception) {
            assertEquals(Message.ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST, exception.getMessage());
        }
    }

    /**
     * Checks if an Activity object is correctly returned when the getActivity method is
     * called and an Activity with the specified activity unique identifier exists in the Session object.
     */
    @Test
    void getActivity_activityExists_ActivityObjectReturned() {
        Command createActivityCommand = Parser.getCommand(CREATE_TEST_ACTIVITY_INPUT_ONE);
        createActivityCommand.run(manager);

        try {
            Activity activity = session.getActivity(1);
            assertEquals("Lunch", activity.getActivityName());
        } catch (InvalidDataException exception) {
            fail();
        }
    }

    // removeActivity()

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when the removeActivity method is
     * called and no Activity objects exists in the Session object.
     */
    @Test
    void removeActivity_noActivityExists_InvalidDataExceptionThrown() {
        try {
            session.removeActivity(1);
            fail();
        } catch (InvalidDataException exception) {
            assertEquals(Message.ERROR_SESSION_EMPTY_ACTIVITY_LIST, exception.getMessage());
        }
    }

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when the removeActivity method is
     * called and no Activity objects with the specified activity unique identifier exists in the Session object.
     */
    @Test
    void removeActivity_activityWithSpecifiedIdDoesNotExist_InvalidDataExceptionThrown() {
        Command createActivityCommand = Parser.getCommand(CREATE_TEST_ACTIVITY_INPUT_ONE);
        createActivityCommand.run(manager);

        try {
            session.removeActivity(2);
            fail();
        } catch (InvalidDataException exception) {
            assertEquals(Message.ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST, exception.getMessage());
        }
    }

    /**
     * Checks if an Activity object and all ActivityCost objects related it are correctly removed from the
     * Session object when the removeActivity method is called and an Activity object with the specified
     * activity unique identifier exists in the Session object.
     */
    @Test
    void removeActivity_activityExists_activityAndActivityCostRemoved() {
        Command createActivityCommandOne = Parser.getCommand(CREATE_TEST_ACTIVITY_INPUT_ONE);
        Command createActivityCommandTwo = Parser.getCommand(CREATE_TEST_ACTIVITY_INPUT_TWO);
        createActivityCommandOne.run(manager);
        createActivityCommandTwo.run(manager);

        try {
            session.removeActivity(1);
        } catch (InvalidDataException exception) {
            fail();
        }

        // Check if Activity object still exists
        try {
            session.getActivity(1);
            fail();
        } catch (InvalidDataException exception) {
            assertEquals(Message.ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST, exception.getMessage());
        }

        // Check if ActivityCost objects still exists
        ArrayList<Person> personList = session.getPersonList();
        for (Person person : personList) {
            try {
                person.removeActivityCost(1);
            } catch (InvalidDataException exception) {
                assertEquals(Message.ERROR_PERSON_ACTIVITY_NOT_FOUND + 1, exception.getMessage());
            }
        }
    }

    // getPersonByName()

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when the getPersonByName method is
     * called and no Person objects with the specified name exists in the Session object.
     */
    @Test
    void getPersonByName_personWithSpecifiedNameDoesNotExist_InvalidDataExceptionThrown() {
        try {
            Person person = session.getPersonByName("David");
            fail();
        } catch (InvalidDataException exception) {
            assertEquals(Message.ERROR_SESSION_PERSON_NOT_IN_LIST, exception.getMessage());
        }
    }

    /**
     * Checks if a Person object is correctly returned when the getPersonByName method is
     * called and a Person object with the specified name exists in the Session object.
     */
    @Test
    void getPersonByName_personWithSpecifiedNameExists_PersonObjectReturned() {
        try {
            Person person = session.getPersonByName("Alice");
            assertEquals("Alice", person.getName());
        } catch (InvalidDataException exception) {
            fail();
        }
    }
}
