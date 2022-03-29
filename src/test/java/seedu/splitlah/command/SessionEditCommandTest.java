package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SessionEditCommandTest {

    Manager manager = new Manager();

    private static final String ORIGINAL_SESSION_NAME = "Class outing";
    private static final String ORIGINAL_SESSION_DATE = "15-02-2022";
    private static final int ORIGINAL_SESSION_PERSONLIST_SIZE = 2;

    private static final String EDITED_SESSION_NAME = "Class gathering";
    private static final String EDITED_SESSION_DATE = "17-02-2022";
    private static final int EDITED_SESSION_PERSONLIST_SIZE = 3;

    /**
     * Creates a session that is stored and managed by the Manager object.
     */
    @BeforeEach
    void setUp() {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);
    }

    /**
     * Checks if session is edited successfully with Name delimiter.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_validCommand_sessionNameEdited() throws InvalidDataException {
        String userInput = "session /edit /sid 1 /n Class gathering";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionEditCommand instance was returned.
        assertEquals(SessionEditCommand.class, command.getClass());
        command.run(manager);

        Session editedSession = manager.getProfile().getSession(1);

        String newNameInSession = editedSession.getSessionName();
        String dateInSession = editedSession.getDateCreated().format(ParserUtils.DATE_FORMAT);
        int personListSizeInSession = editedSession.getPersonList().size();

        assertEquals(EDITED_SESSION_NAME, newNameInSession);
        assertEquals(ORIGINAL_SESSION_DATE, dateInSession);
        assertEquals(ORIGINAL_SESSION_PERSONLIST_SIZE, personListSizeInSession);
    }

    /**
     * Checks if session is edited successfully with Date delimiter.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_validCommand_sessionDateEdited() throws InvalidDataException {
        String userInput = "session /edit /sid 1 /d 17-02-2022";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionEditCommand instance was returned.
        assertEquals(SessionEditCommand.class, command.getClass());
        command.run(manager);

        Session editedSession = manager.getProfile().getSession(1);
        String nameInSession = editedSession.getSessionName();
        String newDateInSession = editedSession.getDateCreated().format(ParserUtils.DATE_FORMAT);
        int personListSizeInSession = editedSession.getPersonList().size();

        assertEquals(ORIGINAL_SESSION_NAME, nameInSession);
        assertEquals(EDITED_SESSION_DATE, newDateInSession);
        assertEquals(ORIGINAL_SESSION_PERSONLIST_SIZE, personListSizeInSession);
    }

    /**
     * Checks if session is edited successfully with Person list delimiter.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_validCommand_sessionPersonListEdited() throws InvalidDataException {
        ArrayList<Person> originalPersonList = new ArrayList<>();
        originalPersonList.add(new Person("alice"));
        originalPersonList.add(new Person("bob"));
        originalPersonList.add(new Person("charlie"));

        String userInput = "session /edit /sid 1 /pl Alice Bob Charlie";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionEditCommand instance was returned.
        assertEquals(SessionEditCommand.class, command.getClass());
        command.run(manager);

        Session editedSession = manager.getProfile().getSession(1);

        String nameInSession = editedSession.getSessionName();
        String dateInSession = editedSession.getDateCreated().format(ParserUtils.DATE_FORMAT);

        assertEquals(ORIGINAL_SESSION_NAME, nameInSession);
        assertEquals(ORIGINAL_SESSION_DATE, dateInSession);

        // Checks if newly edited person list in session has size of 3.
        assertEquals(EDITED_SESSION_PERSONLIST_SIZE, editedSession.getPersonList().size());

        // Check if Person objects in session before edit is still in session after edit
        originalPersonList.removeAll(editedSession.getPersonList());
        assertEquals(0, originalPersonList.size());
    }

    /**
     * Checks if session is not edited when list of persons provided after Person list delimiter
     * does not contain all persons that were originally in the session.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_invalidPersonListArgument_sessionRemainsUnedited() throws InvalidDataException {
        String invalidInput  = "session /edit /sid 1 /pl Alice Charlie";
        Command command = Parser.getCommand(invalidInput);

        // Check if a SessionEditCommand instance was returned.
        assertEquals(SessionEditCommand.class, command.getClass());
        command.run(manager);

        Session editedSession = manager.getProfile().getSession(1);
        String nameInSession = editedSession.getSessionName();
        String dateInSession = editedSession.getDateCreated().format(ParserUtils.DATE_FORMAT);
        int personListSizeInSession = editedSession.getPersonList().size();

        assertEquals(ORIGINAL_SESSION_NAME, nameInSession);
        assertEquals(ORIGINAL_SESSION_DATE, dateInSession);
        assertEquals(ORIGINAL_SESSION_PERSONLIST_SIZE, personListSizeInSession);
    }

    /**
     * Checks if session is not edited when there were duplicate names in Person list delimiter.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_hasOneNameDuplicate_sessionRemainsUnedited() throws InvalidDataException {
        String invalidInput  = "session /edit /sid 1 /pl Alice Alice Bob Charlie";
        Command command = Parser.getCommand(invalidInput);

        // Check if a SessionEditCommand instance was returned.
        assertEquals(SessionEditCommand.class, command.getClass());
        command.run(manager);
        Session editedSession = manager.getProfile().getSession(1);
        String nameInSession = editedSession.getSessionName();
        String dateInSession = editedSession.getDateCreated().format(ParserUtils.DATE_FORMAT);
        int personListSizeInSession = editedSession.getPersonList().size();

        assertEquals(ORIGINAL_SESSION_NAME, nameInSession);
        assertEquals(ORIGINAL_SESSION_DATE, dateInSession);
        assertEquals(ORIGINAL_SESSION_PERSONLIST_SIZE, personListSizeInSession);
    }

    /**
     * Checks if session is not edited when an invalid session unique identifier is provided.
     *
     * @throws InvalidDataException If there are no sessions stored or
     *                              if the session unique identifier specified was not found.
     */
    @Test
    public void run_sessionIdDoesNotExist_sessionRemainsUnedited() throws InvalidDataException {
        String invalidInput  = "session /edit /sid 2 /n Class Gathering";
        Command command = Parser.getCommand(invalidInput);

        // Check if a SessionEditCommand instance was returned.
        assertEquals(SessionEditCommand.class, command.getClass());
        command.run(manager);

        Session editedSession = manager.getProfile().getSession(1);
        String nameInSession = editedSession.getSessionName();
        String dateInSession = editedSession.getDateCreated().format(ParserUtils.DATE_FORMAT);
        int personListSizeInSession = editedSession.getPersonList().size();

        assertEquals(ORIGINAL_SESSION_NAME, nameInSession);
        assertEquals(ORIGINAL_SESSION_DATE, dateInSession);
        assertEquals(ORIGINAL_SESSION_PERSONLIST_SIZE, personListSizeInSession);
    }
}
