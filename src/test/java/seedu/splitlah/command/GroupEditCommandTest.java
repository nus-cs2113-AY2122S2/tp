package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Group;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupEditCommandTest {

    Manager manager = new Manager();

    private static final String ORIGINAL_GROUP_NAME = "grp1";
    private static final int ORIGINAL_GROUP_PERSON_LIST_SIZE = 2;

    private static final String EDITED_GROUP_NAME = "grp_edited";

    /**
     * Creates a group that is stored and managed by the Manager object.
     */
    @BeforeEach
    void setUp() {
        String groupArgs = "group /create /n grp1 /pl Alice Bob";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);
    }

    /**
     * Checks if group is edited successfully with Name delimiter.
     *
     * @throws InvalidDataException If there are no groups stored or
     *                              if the group unique identifier specified was not found.
     */
    @Test
    public void run_validCommand_groupNameEdited() throws InvalidDataException {
        String userInput = "group /edit /gid 1 /n grp_edited";
        Command command = Parser.getCommand(userInput);

        // Check if a GroupEditCommand instance was returned.
        assertEquals(GroupEditCommand.class, command.getClass());
        command.run(manager);

        Group editedGroup = manager.getProfile().getGroup(1);

        String newNameInGroup = editedGroup.getGroupName();
        int personListSizeInGroup = editedGroup.getPersonList().size();

        assertEquals(EDITED_GROUP_NAME, newNameInGroup);
        assertEquals(ORIGINAL_GROUP_PERSON_LIST_SIZE, personListSizeInGroup);
    }

    /**
     * Checks if group is not edited when an invalid group unique identifier is provided.
     *
     * @throws InvalidDataException If there are no groups stored or
     *                              if the group unique identifier specified was not found.
     */
    @Test
    public void run_groupIdDoesNotExist_groupRemainsUnedited() throws InvalidDataException {
        String invalidInput  = "group /edit /gid 2 /n grp_edited";
        Command command = Parser.getCommand(invalidInput);

        // Check if a GroupEditCommand instance was returned.
        assertEquals(GroupEditCommand.class, command.getClass());
        command.run(manager);

        Group editedGroup = manager.getProfile().getGroup(1);
        String nameInGroup = editedGroup.getGroupName();
        int personListSizeInGroup = editedGroup.getPersonCount();

        assertEquals(ORIGINAL_GROUP_NAME, nameInGroup);
        assertEquals(ORIGINAL_GROUP_PERSON_LIST_SIZE, personListSizeInGroup);
    }
}
