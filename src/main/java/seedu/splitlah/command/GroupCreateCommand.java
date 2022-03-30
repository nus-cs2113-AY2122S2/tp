package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.data.Profile;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;

/**
 * Represents a command that creates a Group object from user input and stores it in the Profile object.
 *
 * @author Tianle
 */
public class GroupCreateCommand extends Command {

    public static final String COMMAND_TEXT = "group /create";

    public static final String COMMAND_FORMAT =
        "Syntax: group /create /n [GROUP_NAME] /pl [NAME1 NAME2...]";

    private static final String SUCCESS_MESSAGE =
        "The group was created successfully.\n";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.NAME_DELIMITER,
        ParserUtils.PERSON_LIST_DELIMITER
    };

    private final String groupName;
    private final String[] personNames;

    /**
     * Initializes a GroupCreateCommand object.
     *
     * @param groupName   A String object that represents the group name.
     * @param personNames An array of String objects that represents the involved persons for the group.
     */
    public GroupCreateCommand(String groupName, String[] personNames) {
        assert groupName != null : Message.ASSERT_GROUPCREATE_GROUP_NAME_NULL;
        assert personNames != null : Message.ASSERT_GROUPCREATE_PERSONLIST_NULL;
        this.groupName = groupName;
        this.personNames = personNames;
    }

    /**
     * Prepares user arguments for the creation of a GroupCreateCommand object.
     *
     * @param commandArgs A String object that represents the user's arguments.
     * @return A GroupCreateCommand object if group name and person list were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            String parsedGroupName = Parser.parseName(commandArgs);
            String[] parsedNameList = Parser.parsePersonList(commandArgs);
            return new GroupCreateCommand(parsedGroupName, parsedNameList);
        } catch (InvalidFormatException formatException) {
            String invalidCommandMessage = formatException.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }

    /**
     * Runs the command to create a Group object to be stored in the list of groups managed by the Profile Object.
     * Checks if array of names has duplicates and if group name exists.
     * If check fails, no group will be created and prints error message.
     * Else a group is created and prints success message.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        Profile profile = manager.getProfile();

        boolean hasDuplicates = PersonList.hasNameDuplicates(personNames);
        if (hasDuplicates) {
            ui.printlnMessage(Message.ERROR_PERSONLIST_DUPLICATE_NAME_IN_GROUP);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_PERSONLIST_NAME_DUPLICATE_EXISTS_IN_GROUP);
            return;
        }

        boolean isGroupExists = profile.hasGroupName(groupName);
        if (isGroupExists) {
            ui.printlnMessage(Message.ERROR_PROFILE_DUPLICATE_GROUP);
            Manager.getLogger().log(Level.FINEST,Message.LOGGER_GROUPCREATE_DUPLICATE_NAMES_IN_GROUP_LIST);
            return;
        }
        PersonList personList = new PersonList();
        personList.convertToPersonList(personNames);
        int newGroupId = profile.getNewGroupId();
        Group newGroup = new Group(groupName, newGroupId, personList);
        profile.addGroup(newGroup);
        manager.saveProfile();
        ui.printlnMessageWithDivider(SUCCESS_MESSAGE + newGroup);
        Manager.getLogger().log(Level.FINEST,Message.LOGGER_GROUPCREATE_GROUP_ADDED + newGroupId);
    }
}
