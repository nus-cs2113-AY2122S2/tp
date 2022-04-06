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
    public static final String DELETE_CONFIRMATION_INPUT_ERROR = "Invalid Input. Please enter yes or no.";

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
     * For GpaCommand.
     */
    public static final String GPA_MESSAGE = "Your GPA is %.02f! :)";

    /**
     * For GradeCommand.
     */
    public static final String GRADE_ADDED_MESSAGE = "Your grade for %s has been added.";
    public static final String GRADE_CHANGED_MESSAGE = "Your grade for %s has been changed.";

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
    public static final String HELP_NOTE = "Compulsory parameters are fully capitalised: e.g. MODULE_CODE.\n"
            + "Optional parameters are in square brackets: e.g. [-d MODULE_DESCRIPTION]";
    public static final String ADD_HELP = "Adds a module or task as indicated by the command input.\n"
            + "Format to add module: add mod MODULE_CODE MODULAR_CREDITS [-d \"MODULE_DESCRIPTION\"]\n"
            + "Format to add task:   add task \"TASK_NAME\" [-m MODULE_CODE] [-d \"TASK_DESCRIPTION\"]"
            + " [-t \"ESTIMATED_WORKING_TIME\"]";
    public static final String DELETE_HELP = "Deletes a module or task as indicated by command input.\n"
            + "Format to delete a module: del mod MODULE_CODE\n"
            + "Format to delete a task:   del task TASK_NUMBER [-m MODULE_CODE]";
    public static final String EDIT_HELP = "Edits a module or task as indicated by command input.\n"
            + "Format to edit a module: edit mod MODULE_CODE -d \"MODULE_DESCRIPTION\"\n"
            + "Format to edit a task:   edit task TASK_INDEX [-m MODULE_CODE]"
            + " (-n \"TASK_NAME\" | -d \"TASK_DESCRIPTION\" | -t \"ESTIMATED_WORKING_TIME\")";
    public static final String EXIT_HELP = "Exits the program.\nFormat to exit program: exit";
    public static final String GRADE_HELP = "Sets the grade for the specified module.\n"
            + "Accepted values: A+, A, B+, B, B-, C+, C, D+, D, F, S, U, CS, CU\n"
            + "Format to set a module's grade: grade MODULE_CODE MODULE_GRADE";
    public static final String GPA_HELP = "Computes and displays the GPA based the inputted grades of all modules.\n"
            + "Modules without any assigned grade are omitted from the calculation.\n"
            + "Format to display GPA : gpa\n";
    public static final String LIST_HELP = "Displays a list of tasks, grouped by module code.\n"
            + "Completed tasks may or may not be shown depending on current user preferences.\n"
            + "If tag name is provided, list will only display tasks containing the tag name.\n"
            + "Format to list all tasks: list\n"
            + "Format to list task containing a tag: list TAG_NAME";
    public static final String MARK_HELP = "Mark a task with the given task number from the specified module."
            + "If no module code is given, the task to be marked will be drawn from the \"general tasks\" list.\n"
            + "Format to mark a task as completed:   mark c TASK_NUMBER [-m MODULE_CODE]\n"
            + "Format to mark a task as uncompleted: mark u TASK_NUMBER [-m MODULE_CODE]";
    public static final String RESET_HELP = "Removes all modules and tasks.\n"
            + "Format to remove all modules and tasks: reset";
    public static final String SAVE_HELP = "Saves your modules and tasks.\n"
            + "Format to save: save";
    public static final String HELP = "Displays help and format for selected command.\n"
            + "Format to display help for specific command: help COMMAND\n"
            + "Available commands: exit, add, del, edit, grade, gpa, help, list, mark, option, reset, save, tag";
    public static final String TAG_HELP = "Set a custom tag for your tasks. The tag cannot contain whitespace.\n"
            + "Format to add a tag: tag add TASK_INDEX [-m MODULE_CODE] TAG_NAME\n"
            + "Format to delete a tag: tag del TASK_INDEX [-m MODULE_CODE] TAG_NAME";
    public static final String HELP_EXCEPTION = "Sorry, but no help exists for that command.";
    public static final String HELP_COMMAND_ARGUMENT = "command";
    public static final String OPTION_HELP = "View and edit program configuration options.\n"
            + "Format to view all available configs: option\n"
            + "Format to view details for a specific config option: option CONFIG_NAME\n"
            + "Format to set a config option: option CONFIG_NAME=NEW_VALUE\n\n"
            + "Available configs:\n";


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
     * For exceptions.
     */
    public static final String ERROR_NO_SUCH_MODULE = "Sorry, no such module exists ._.";
    public static final String ERROR_NO_SUCH_TASK = "Sorry, no such task exists ._.";
    public static final String ERROR_PARSE_FAILED = "This parse failed 0_0";
    public static final String ERROR_PARSE_INVALID_PARAM = "\nInvalid compulsory parameters. "
            + "Please check and try again.";
    public static final String ERROR_ADDITIONAL_PARAMETER = "Sorry, this command should have no parameters.";
    public static final String ERROR_PARSE_STRING = "\nError at '%s'.\nPlease check and try again.";
    public static final String ERROR_UNKNOWN_COMMAND = "Sorry, I don't understand the following command:";
    public static final String ERROR_UNSUPPORTED_RESULT_TYPE = "Sorry, the value \"%s\" is not supported for "
            + "configuration \"%s\".";
    public static final String ERROR_WRITE_FILE = "Error writing to file...";
    public static final String ERROR_READ_FILE = "Error reading from file...";
    public static final String ERROR_FILE_CREATE_FAIL = "Sorry, file creation failed...";
    public static final String ERROR_NO_SUCH_TAG = "Sorry, no such tag exists ._.";
    public static final String ERROR_UNKNOWN_CONFIGURATION_GROUP = "Sorry, no config named \"%s\" exists.\n"
            + "View all available config settings with \"option\".";
    public static final String ERROR_MODULE_LIST_EMPTY = "Sorry, you have 0 MCs counted towards your GPA ._.\n"
            + "Please add some modules or grades!";
    public static final String ERROR_PARSE_INT_FAILED = "\nInvalid %s. Please check and try again.";
    public static final String ERROR_MODULAR_CREDITS_FAILED = "modular credits";
    public static final String ERROR_TASK_NUMBER_FAILED = "task number";
    public static final String ERROR_WRONG_DURATION_FORMAT = "Sorry, the estimated time is in wrong format ._.";
    public static final String ERROR_DUPLICATE_MODULE = "Multiple modules with identical module codes found. "
            + "Aborting load...";


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
    public static final String CONFIGURATION_GROUP_WORD = "configurationGroupWord";
    public static final String NEW_VALUE = "newValue";
    public static final String COMPLETED_FLAG = "c";
    public static final String UNCOMPLETED_FLAG = "u";
    public static final String ARGUMENT = "arguments";
    public static final String TAG_NAME = "tagName";
    public static final String TAG_OPERATION = "tagOperation";
    public static final String INVALID = "invalid";
    public static final String COMMAND_WORD = "commandWord";
    public static final String EXIT_COMMAND_WORD = "exit";
    public static final String ADD_COMMAND_WORD = "add";
    public static final String DELETE_COMMAND_WORD = "del";
    public static final String EDIT_COMMAND_WORD = "edit";
    public static final String GPA_COMMAND_WORD = "gpa";
    public static final String GRADE_COMMAND_WORD = "grade";
    public static final String LIST_COMMAND_WORD = "list";
    public static final String MARK_COMMAND_WORD = "mark";
    public static final String RESET_COMMAND_WORD = "reset";
    public static final String HELP_COMMAND_WORD = "help";
    public static final String SAVE_COMMAND_WORD = "save";
    public static final String TAG_COMMAND_WORD = "tag";
    public static final String OPTION_COMMAND_WORD = "option";


    /**
     * For grades.
     */
    public static final String DASH = "-";
    public static final String PLUS = "+";
    public static final String PLUS_STR = "PLUS";
    public static final String MINUS_STR = "MINUS";
    public static final String NOT_ENTERED_STR = "NOT_ENTERED";

    /**
     * For option.
     */
    public static final String DESCRIPTION_FORMAT = "%s: %s";
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String SHOW_COMPLETED_TASKS_NAME = "SHOW_COMPLETED_TASKS";
    public static final String SHOW_COMPLETED_TASKS_EXPLAIN = "Determines if completed tasks should be displayed"
            + " by \"list\" or not.";
    public static final String SHOW_COMPLETED_TASKS_TRUE = "Show completed tasks";
    public static final String SHOW_COMPLETED_TASKS_FALSE = "Hide completed tasks";

    /**
     * For TaskDuration.
     */
    public static final String DURATION_GROUP_WORD = "duration";
    public static final String DURATION_UNIT_GROUP_WORD = "durationUnit";
    public static final String TO_STRING_FORMAT_WITH_HOUR_AND_MINUTE = "%d hours %d minutes";
    public static final String TO_STRING_FORMAT_WITH_HOUR_ONLY = "%d hours";
    public static final String TO_STRING_FORMAT_WITH_MINUTE_ONLY = "%d minutes";
    public static final String DURATION_STRING_FORMAT = "(?<duration>[1-9]\\d*\\.?\\d*|0\\.\\d*[1-9])"
            + "\\s*(?<durationUnit>.*)";

    /**
     * General strings.
     */
    public static final String INDENT = "    ";
    public static final String LS = System.lineSeparator();
    public static final String LINE = "____________________________________________________________";
}
