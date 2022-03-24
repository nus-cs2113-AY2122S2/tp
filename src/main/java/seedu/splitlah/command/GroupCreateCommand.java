package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.PersonList;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

/**
 * Represents a command that creates a Group object from user input and stores it in the Profile object.
 *
 * @author Tianle
 */
public class GroupCreateCommand extends Command {

    public static final String COMMAND_TEXT = "group /create";

    public static final String COMMAND_FORMAT =
        "Syntax: group /create /n [GROUP_NAME] /pl [NAME1 NAME2 …]";

    private static final String SUCCESS_MESSAGE =
        "The group was created successfully.\n";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.NAME_DELIMITER,
        ParserUtils.PERSON_LIST_DELIMITER
    };

    private String groupName;
    private String[] personNames;

    /**
     * Initializes a GroupCreateCommand object.
     *
     * @param groupName   A String object that represents the group name.
     * @param personNames An array of String objects that represents the involved persons for the group.
     */
    public GroupCreateCommand(String groupName, String[] personNames) {
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
        boolean hasDuplicates = PersonList.hasNameDuplicates(personNames);
        if (hasDuplicates) {
            manager.getUi().printlnMessage(Message.ERROR_GROUPCREATE_DUPLICATE_NAMES);
            return;
        }

        PersonList personList = new PersonList();
        personList.convertToPersonList(personNames);

        boolean isGroupExists = manager.getProfile().hasGroupName(groupName);

        if (isGroupExists) {
            manager.getUi().printlnMessage(Message.ERROR_GROUPCREATE_DUPLICATE_GROUP_NAME);
            return;
        }
        int newGroupId = manager.getProfile().getNewGroupId();

        Group newGroup = new Group(groupName, newGroupId, personList);
        manager.getProfile().addGroup(newGroup);
        manager.saveProfile();
        manager.getUi().printlnMessageWithDivider(SUCCESS_MESSAGE + newGroup);
    }
}
