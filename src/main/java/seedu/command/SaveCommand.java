package seedu.command;

/**
 * Subclass of Command. Saves the current state of application into a JSON file.
 */
public class SaveCommand extends Command {
    public static final String COMMAND_WORD = "save";
    public static final String COMMAND_DESCRIPTION = ": Saves current state of application. "
            + System.lineSeparator()
            + "Parameters: NIL" + System.lineSeparator()
            + "Example: "
            + "save";
    public static final String SUCCESSFUL_SAVE = "Successfully saved.";

    /**
     * Saves the current state of application into JSON file.
     * @return CommandResult with successful save message.
     */
    public CommandResult execute() {
        storage.saveData(equipmentManager);
        return new CommandResult(SUCCESSFUL_SAVE);
    }
}
