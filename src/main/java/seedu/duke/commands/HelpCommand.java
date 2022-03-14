package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.ModuleList;

public class HelpCommand extends Command {

    //Should be removed once new public string constant class is created
    protected static final String EXIT_COMMAND_WORD = "exit";
    protected static final String ADD_COMMAND_WORD = "add";
    protected static final String DELETE_COMMAND_WORD = "del";
    protected static final String LIST_COMMAND_WORD = "list";
    protected static final String MARK_COMMAND_WORD = "mark";
    protected static final String HELP_COMMAND_WORD = "help";
    protected static final String HELP_NOTE = "Compulsory Flags start with \"/\". Optional Flags start with \"-\".\n"
            + "Compulsory Parameters are fully capitalised: e.g. MODULE_CODE.\n"
            + "Optional Parameters are in square brackets: e.g. [-m MODULE_DESCRIPTION]";
    protected static final String EXIT_HELP = "Exits the program.\nFormat to exit program: exit";
    protected static final String ADD_HELP = "Adds an object as indicated by the command input.\n"
            + "Format to add module: add /m MODULE_CODE [-d \"MODULE_DESCRIPTION\"]\n"
            + "Format to add task:   add /t TASK_NAME [-d \"TASK_DESCRIPTION\"] [-t \"ESTIMATED_WORKING_TIME\"]"
            + " [-m MODULE_CODE]";
    protected static final String DELETE_HELP = "Deletes an object as indicated by command input.\n"
            + "Format to delete a module: del /m MODULE_CODE\n"
            + "Format to delete a task:   del /t TASK_NUMBER [-m MODULE_CODE]";
    protected static final String LIST_HELP = "Displays a list of all tasks, grouped by module code.\n"
            + "Format to list all tasks: list";
    protected static final String MARK_HELP = "Mark a task with the given task number from the specified module."
            + "If no module code is given, the task to be marked will be drawn from the \"general tasks\" list.\n"
            + "Format to mark a task as completed:   mark /c TASK_NUMBER [-m MODULE_CODE]\n"
            + "Format to mark a task as uncompleted: mark /u TASK_NUMBER [-m MODULE_CODE]";
    protected static final String HELP_HELP = "Displays help and format for selected command.\n"
            + "Format to display help for specific: help COMMAND\n"
            + "Available commands: exit, add, del, list, mark, help";
    protected static final String HELP_EXCEPTION = "Sorry, but no help exists for that command.";

    private final String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        System.out.println(HELP_NOTE);
        switch (command) {
        case EXIT_COMMAND_WORD:
            return new CommandResult(EXIT_HELP);
        case ADD_COMMAND_WORD:
            return new CommandResult(ADD_HELP);
        case DELETE_COMMAND_WORD:
            return new CommandResult(DELETE_HELP);
        case LIST_COMMAND_WORD:
            return new CommandResult(LIST_HELP);
        case MARK_COMMAND_WORD:
            return new CommandResult(MARK_HELP);
        case HELP_COMMAND_WORD:
            return new CommandResult(HELP_HELP);
        default:
            throw new ModHappyException(HELP_EXCEPTION);
        }
    }
}
