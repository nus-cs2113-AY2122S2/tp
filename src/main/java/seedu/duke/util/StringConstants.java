package seedu.duke.util;

public class StringConstants {
    /**
     * File paths for data files.
     */
    public static final String TASK_PATH = "data/task.json";
    public static final String MODULE_PATH = "data/module.json";
    public static final String CONFIGURATION_PATH = "data/configuration.json";
    public static final String TASK_TEST_PATH = "data/test/task.json";
    public static final String MODULE_TEST_PATH = "data/test/module.json";
    public static final String CONFIGURATION_TEST_PATH = "data/test/configuration.json";

    /**
     * For start and exit of program.
     */
    public static final String HELLO_MESSAGE = "Hello, welcome to Mod Happy!";
    public static final String GOOD_BYE_MESSAGE = "See you later!";
    public static final String INIT_FAILED_MESSAGE = "Failed to start Mod Happy...";

    /**
     * For loading of data.
     */
    public static final String MODULE_DATA_LOAD_FAILED = "Failed to load module data. "
            + "Empty module list loaded instead.";
    public static final String MODULE_DATA_LOAD_SUCCESS = "Successfully loaded module data!";
    public static final String TASK_DATA_LOAD_FAILED = "Failed to load general task data. "
            + "Empty list of general tasks loaded instead.";
    public static final String TASK_DATA_LOAD_SUCCESS = "Successfully loaded general task data!";
    public static final String CONFIGURATION_DATA_LOAD_FAILED = "Failed to load configuration data. "
            + "Default config values loaded instead.";
    public static final String CONFIGURATION_DATA_LOAD_SUCCESS = "Successfully loaded configuration data!";
    public static final String NO_CONFIG_DATA_FILE = "No saved config data found. Default config values loaded.";


    /**
     * For AddCommand.
     */
    public static final String ADD_TASK_MESSAGE_TOP = "Hey! I have added this task under %s!";
    public static final String ADD_TASK_MESSAGE_BOTTOM = "Now you have %d task(s) in your list!";
    public static final String ADD_MODULE_MESSAGE_TOP = "Hey! I have added this module!";
    public static final String MODULE_ALREADY_EXISTS = "A module with that name already exists...";
    public static final String ESTIMATED_WORKING_TIME = "Estimated working time: ";


    /**
     * For DeleteCommand.
     */
    public static final String DELETE_MESSAGE = "%s has been deleted.";
    public static final String DELETE_ABORT = "Deletion has been cancelled.";
    public static final String DELETE_CONFIRMATION = "%s contains task(s).\n"
            + "Are you sure you want to delete this? (yes/no)";

    /**
     * For GradeCommand.
     */
    public static final String GRADE_ADDED_MESSAGE = "Your grade for %s has been added.";
    public static final String GRADE_CHANGED_MESSAGE = "Your grade for %s has been changed.";

    /**
     * For EditCommand.
     */
    public static final String EDIT_TASK_SUCCESS = "The %s of %s has been changed.";
    public static final String EDIT_MODULE_SUCCESS = "The description of %s has been changed.";
    public static final String EDIT_TASK_WITH_MODULE_SUCCESS = "The %s of %s from %s has been changed.";
    public static final String TASK_NAME_STR = "task name";
    public static final String TASK_DESCRIPTION_STR = "description";
    public static final String ESTIMATED_WORKING_TIME_STR = "estimated working time";

    /**
     * For ExitCommand.
     */
    public static final String READY_EXIT = "I am ready to exit *_*";

    /**
     * For ListCommand.
     */
    public static final String LIST_MESSAGE = "Ok! Here are the task(s) in your list:\n%s";
    public static final String EMPTY_LIST = "(empty)";
    public static final String HIDDEN_TASKS_COUNT = "--- %d completed task(s) hidden ---";
    public static final String LIST_ARGUMENT = "listArgument";

    /**
     * For MarkCommand.
     */
    public static final String MARK_MESSAGE_TOP = "Nice! I have marked this task as completed!";
    public static final String UNMARK_MESSAGE_TOP = "Ok! I have marked this task for you as uncompleted!";
    public static final String ICON_UNCOMPLETED = "( )";
    public static final String ICON_COMPLETED = "(X)";

    /**
     * For ResetCommand.
     */
    public static final String RESET_MESSAGE = "All modules and tasks have been removed.";

    /**
     * For HelpCommand.
     */
    public static final String HELP_NOTE = "Compulsory flags start with \"/\". Optional flags start with \"-\".\n"
            + "Compulsory parameters are fully capitalised: e.g. MODULE_CODE.\n"
            + "Optional parameters are in square brackets: e.g. [-m MODULE_DESCRIPTION]";
    public static final String EXIT_HELP = "Exits the program.\nFormat to exit program: exit";
    public static final String ADD_HELP = "Adds a module or task as indicated by the command input.\n"
            + "Format to add module: add /m MODULE_CODE MODULAR_CREDITS [-d \"MODULE_DESCRIPTION\"]\n"
            + "Format to add task:   add /t \"TASK_NAME\" [-d \"TASK_DESCRIPTION\"] [-t \"ESTIMATED_WORKING_TIME\"]"
            + " [-m MODULE_CODE]";
    public static final String DELETE_HELP = "Deletes a module or task as indicated by command input.\n"
            + "Format to delete a module: del /m MODULE_CODE\n"
            + "Format to delete a task:   del /t TASK_NUMBER [-m MODULE_CODE]";
    public static final String EDIT_HELP = "Edits a module or task as indicated by command input.\n"
            + "Format to edit a module: edit /m MODULE_CODE -d \"MODULE_DESCRIPTION\"\n"
            + "Format to edit a task:   edit /t TASK_INDEX"
            + " (-n \"TASK_NAME\" or -d \"TASK_DESCRIPTION\" or -t \"ESTIMATED_WORKING_TIME\") [-m MODULE_CODE]";
    public static final String GRADE_HELP = "Adds/Changes the grade for the specified module.\n"
            + "Format to add/change a grade: grade /m MODULE_CODE /g MODULE_GRADE";
    public static final String LIST_HELP = "Displays a list of all tasks, grouped by module code.\n"
            + "If tag name is provided, list will only display tasks containing the tag name.\n"
            + "The tag name cannot contain whitespace.\n"
            + "Format to list all tasks: list\n"
            + "Format to list task containing a tag: list \"TAG_NAME\"";
    public static final String MARK_HELP = "Mark a task with the given task number from the specified module."
            + "If no module code is given, the task to be marked will be drawn from the \"general tasks\" list.\n"
            + "Format to mark a task as completed:   mark /c TASK_NUMBER [-m MODULE_CODE]\n"
            + "Format to mark a task as uncompleted: mark /u TASK_NUMBER [-m MODULE_CODE]";
    public static final String RESET_HELP = "Removes all modules and tasks.\n"
            + "Format to remove all modules and tasks: reset";
    public static final String SAVE_HELP = "Saves your modules and tasks.\n"
            + "Format to save: save";
    public static final String HELP = "Displays help and format for selected command.\n"
            + "Format to display help for specific command: help COMMAND\n"
            + "Available commands: exit, add, del, edit, grade, list, mark, save, help, reset, tag";
    public static final String TAG_HELP = "Set a custom tag for your tasks. The tag cannot contain whitespace.\n"
            + "Format to add a tag: tag add TASK_INDEX [-m MODULE_CODE] \"TAG_NAME\"\n"
            + "Format to delete a tag: tag del TASK_INDEX [-m MODULE_CODE] \"TAG_NAME\"";
    public static final String HELP_EXCEPTION = "Sorry, but no help exists for that command.";
    public static final String HELP_COMMAND_ARGUMENT = "command";

    public static final String OPTION_HELP = "View and edit program configuration options.\n"
            + "Format to view all available configs: option\n"
            + "Format to view details for a specific config option: option CONFIG_NAME\n\n"
            + "Format to set a config option: option CONFIG_NAME=NEW_VALUE";

    /**
     * For SaveCommand.
     */
    public static final String MODULE_DATA_SAVE_FAILED = "Failed to write module data to file. "
            + "Your modules were NOT saved!";
    public static final String MODULE_DATA_SAVE_SUCCESS = "Module data written to file.";
    public static final String TASK_DATA_SAVE_FAILED = "Failed to write general task data to file. "
            + "Your general tasks were NOT saved!";
    public static final String TASK_DATA_SAVE_SUCCESS = "General tasks written to file.";
    public static final String CONFIGURATION_DATA_SAVE_FAILED = "Failed to write config options to file. "
            + "Your preferences were NOT saved!";
    public static final String CONFIGURATION_DATA_SAVE_SUCCESS = "Config options written to file.";


    /**
     * For OptionCommand.
     */
    public static final String OPTION_SET_SUCCESS = "Preferences updated: ";
    public static final String OPTION_CHECK_CONFIGURATIONS = "Available config settings: ";


    /**
     * For TagCommand.
     */
    public static final String ADD_TAG_MESSAGE = "Tag \"%s\" added:\n%s.";
    public static final String DEL_TAG_MESSAGE = "Tag \"%s\" removed:\n%s";

    /**
     * For CommandResult.
     */
    public static final String ARRAYLIST_RESULT = "ArrayList";
    public static final String STRING_RESULT = "String";


    /**
     * For exceptions.
     */
    public static final String ERROR_NO_SUCH_MODULE = "Sorry, no such module exists ._.";
    public static final String ERROR_NO_SUCH_TASK = "Sorry, no such task exists ._.";
    public static final String ERROR_PARSE_FAILED = "This parse failed 0_0";
    public static final String ERROR_UNKNOWN_COMMAND = "Sorry, I don't understand the following command:";
    public static final String ERROR_UNSUPPORTED_RESULT_TYPE = "Sorry, I don't understand the result format:";
    public static final String ERROR_WRITE_FILE = "Error writing to file...";
    public static final String ERROR_READ_FILE = "Error reading from file...";
    public static final String ERROR_FILE_CREATE_FAIL = "Sorry, file creation failed...";
    public static final String ERROR_NO_SUCH_TAG = "Sorry, no such tag exists ._.";
    public static final String ERROR_UNKNOWN_CONFIGURATION_GROUP = "Sorry, no config named \"%s\" exists.\n"
            + "View all available config settings with \"option\".";


    /**
     * For parsers.
     */
    public static final String TASK_NAME = "taskName";
    public static final String TASK_DESCRIPTION = "taskDescription";
    public static final String TASK_WORKING_TIME = "estimatedWorkingTime";
    public static final String TASK_MODULE = "taskModule";
    public static final String MODULE_CODE = "moduleCode";
    public static final String MODULE_DESCRIPTION = "moduleDescription";
    public static final String MODULAR_CREDIT = "modularCredit";
    public static final String MODULE_GRADE = "moduleGrade";
    public static final String TASK_NUMBER = "taskNumber";
    public static final String FLAG = "flag";
    public static final String TASK_INDEX = "taskIndex";
    public static final String COMPLETED_FLAG = "/c";
    public static final String UNCOMPLETED_FLAG = "/u";
    public static final String ARGUMENT = "arguments";
    public static final String TAG_NAME = "tagName";
    public static final String TAG_OPERATION = "tagOperation";
    public static final String COMMAND_WORD = "commandWord";
    public static final String EXIT_COMMAND_WORD = "exit";
    public static final String ADD_COMMAND_WORD = "add";
    public static final String DELETE_COMMAND_WORD = "del";
    public static final String EDIT_COMMAND_WORD = "edit";
    public static final String GRADE_COMMAND_WORD = "grade";
    public static final String LIST_COMMAND_WORD = "list";
    public static final String MARK_COMMAND_WORD = "mark";
    public static final String RESET_COMMAND_WORD = "reset";
    public static final String HELP_COMMAND_WORD = "help";
    public static final String SAVE_COMMAND_WORD = "save";
    public static final String TAG_COMMAND_WORD = "tag";
    public static final String OPTION_COMMAND_WORD = "option";

    /**
     * For Grades.
     */
    public static final String DASH = "-";
    public static final String PLUS = "+";
    public static final String PLUS_STR = "PLUS";
    public static final String MINUS_STR = "MINUS";
    public static final String NOT_ENTERED_STR = "NOT_ENTERED";

    /**
     * For options.
     */
    public static final String DESCRIPTION_FORMAT = "%s: %s";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String COMPLETED_TASKS_SHOWN_NAME = "COMPLETED_TASKS_SHOWN";
    public static final String COMPLETED_TASKS_SHOWN_EXPLAIN = "Whether or not completed tasks should be displayed"
            + " by \"list\".";
    public static final String COMPLETED_TASKS_SHOWN_TRUE = "Show completed tasks";
    public static final String COMPLETED_TASKS_SHOWN_FALSE = "Hide completed tasks";

    /**
     * General strings.
     */
    public static final String STRING = "String";
    public static final String INDENT = "    ";
    public static final String NULL_STRING = "";
    public static final String LS = System.lineSeparator();
    public static final String COLON = ":";
    public static final String LINE = "____________________________________________________________";
}
