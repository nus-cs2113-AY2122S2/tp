package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.data.Group;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

/**
 * Represents a command object that edits a Group object.
 *
 * @author Tianle
 */
public class GroupEditCommand extends Command {

    public static final String COMMAND_TEXT = "group /edit";

    public static final String COMMAND_FORMAT = "Syntax: group /edit";

    private static final String COMMAND_SUCCESS = "The group was edited successfully.\n";

    private String groupName;
    private final String[] involvedList;
    private int groupId;

    public GroupEditCommand(String[] involvedList, String groupName, int groupId) {
        this.involvedList = involvedList;
        this.groupName = groupName;
        this.groupId = groupId;
    }

    /**
     * Runs the command with the group identifier as provided by the user input.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage objects.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        Group group = null;
        try {
            group = manager.getProfile().getGroup(groupId);
        } catch (InvalidDataException invalidDataException) {
            ui.printlnMessageWithDivider(invalidDataException.getMessage());
            return;
        }

        if (involvedList != null) {
            boolean hasDuplicates = PersonList.hasNameDuplicates(involvedList);
            if (hasDuplicates) {
                ui.printlnMessage(Message.ERROR_GROUPEDIT_DUPLICATE_NAME);
                return;
            }
            PersonList newPersonList = new PersonList();
            newPersonList.convertToPersonList(involvedList);
            if (!newPersonList.isSuperset(group.getPersonList())) {
                ui.printlnMessageWithDivider(Message.ERROR_GROUPEDIT_INVALID_PERSONLIST);
                return;
            } else {
                for (Person person : newPersonList.getPersonList()) {
                    group.addPerson(person);
                }
            }
        }
        if (groupName != null) {
            group.setGroupName(groupName);
        }
        manager.saveProfile();
        ui.printlnMessageWithDivider(COMMAND_SUCCESS + "\n" + group);
    }
}

