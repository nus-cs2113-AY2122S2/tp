package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;

/**
 * Represents a command which deletes a Group object from a list of groups managed by the Profile object.
 *
 * @author Tianle
 */
public class GroupDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "group /delete";

    public static final String COMMAND_FORMAT = "Syntax: group /delete /gid [GROUP_ID]";

    private static final String SUCCESS_MESSAGE = "The group was deleted successfully.";

    public static final String[] COMMAND_DELIMITERS = {
        ParserUtils.GROUP_ID_DELIMITER,
    };

    private int groupId;

    /**
     * Initializes a GroupDeleteCommand object.
     *
     * @param groupId An integer that represents the group unique identifier for the group to be deleted.
     */
    public GroupDeleteCommand(int groupId) {
        // TODO: Add assert to check whether groupId is valid.
        this.groupId = groupId;
    }

    /**
     * Prepares user arguments for the creation of an GroupDeleteCommand object.
     *
     * @param commandArgs The user's arguments.
     * @return A GroupDeleteCommand object if necessary parameters were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        try {
            int groupId = Parser.parseGroupId(commandArgs);
            return new GroupDeleteCommand(groupId);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT);
        }
    }

    /**
     * Runs the command to delete a Group object from the list of groups managed by a Manager Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        try {
            manager.getProfile().removeGroup(groupId);
            manager.saveProfile();
            manager.getUi().printlnMessageWithDivider(SUCCESS_MESSAGE);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessageWithDivider(e.getMessage());
        }
    }
}
