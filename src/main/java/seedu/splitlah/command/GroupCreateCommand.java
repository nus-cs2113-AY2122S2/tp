package seedu.splitlah.command;

import seedu.splitlah.data.Group;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Represents a command that creates a Group object from user input and stores it in the Profile object.
 *
 * @author Tianle
 */
public class GroupCreateCommand extends Command {

    public static final String COMMAND_TEXT = "group /create";

    public static final String COMMAND_FORMAT =
        "Syntax: group /create /n [GROUP_NAME] /pl [NAME1 NAME2 …]";

    private static final String COMMAND_SUCCESS =
        "The group was created successfully.\n";

    public static final String[] COMMAND_DELIMITERS = {
        Parser.NAME_DELIMITER,
        Parser.PERSON_LIST_DELIMITER
    };

    private String groupName;
    private String[] personNames;


    /**
     * Initializes a GroupCreateCommand.
     *
     * @param groupName A String object that represents the group name.
     * @param personNames A String object array that represents the involved persons for the group.
     */
    public GroupCreateCommand(String groupName, String[] personNames) {
        this.groupName = groupName;
        this.personNames = personNames;
    }

    /**
     * Converts a String object array of names to a list of Person objects.
     *
     * @return An ArrayList of Person objects.
     */
    private ArrayList<Person> convertToListOfPerson() {
        ArrayList<Person> personList = new ArrayList<>();
        for (String name : personNames) {
            Person newPerson = new Person(name);
            personList.add(newPerson);
        }
        return personList;
    }

    /**
     * Checks if String object array of names has duplicated names.
     *
     * @return true if it contains duplicates, false otherwise.
     */
    private boolean hasNameDuplicates() {
        Set<String> nameSet = new HashSet<>();
        for (String name : personNames) {
            String nameToBeAdded = name.toLowerCase();
            if (!nameSet.add(nameToBeAdded)) {
                return true;
            }
        }
        assert nameSet.size() == personNames.length :
            Message.ASSERT_SESSIONCREATE_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED;
        return false;
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
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        boolean hasDuplicates = hasNameDuplicates();
        if (hasDuplicates) {
            manager.getUi().printlnMessage(Message.ERROR_GROUPCREATE_DUPLICATE_NAMES);
            return;
        }

        ArrayList<Person> personList = convertToListOfPerson();

        boolean isGroupExists = manager.getProfile().hasGroupName(groupName);

        if (isGroupExists) {
            manager.getUi().printlnMessage(Message.ERROR_GROUPCREATE_DUPLICATE_GROUP_NAME);
            return;
        }
        int newGroupId = manager.getProfile().getNewGroupId();
        boolean isGroupIdExists = manager.getProfile().hasGroupId(newGroupId);
        if (isGroupIdExists) {
            manager.getUi().printlnMessage(Message.ERROR_GROUPCREATE_DUPLICATE_GROUP_ID);
            return;
        }

        Group newGroup = new Group(groupName, newGroupId, personList);
        manager.getProfile().addGroup(newGroup);
        manager.getUi().printlnMessageWithDivider(COMMAND_SUCCESS + newGroup);
    }
}
