package seedu.duke.util;

public class StringConstants {
    /**.
     * For start and exit of program
     */
    public static final String HELLO_MESSAGE = "Hello, this is Mod Happy (○'◡'○)ﾉ";
    public static final String GOOD_BYE_MESSAGE = "See you later ヾ(*´▽'*)ﾉ";
    public static final String INITIAL_FAILED_MESSAGE = "Failed to start Mod Happy (..•˘_˘•..)";

    /**.
     * For addCommand
     */
    public static final String ADD_TASK_MESSAGE_TOP = "Hey! I have added this task under %s!";
    public static final String ADD_TASK_MESSAGE_BOTTOM = "Now you have %d task(s) in your list!";
    public static final String ADD_MODULE_MESSAGE_TOP = "Hey! I have added this module!";
    public static final String MODULE_ALREADY_EXISTS = "A module with that name already exists...";
    public static final String ESTIMATED_WORKING_TIME = "Estimated Working Time: ";

    /**.
     * For deleteCommand
     */
    public static final String DELETE_MESSAGE = " has been deleted.";

    /**.
     * For exitCommand
     */
    public static final String READY_EXIT = "I am ready to exit *_*";

    /**.
     * For listCommand
     */
    public static final String LIST_MESSAGE_TOP = "Ok! Here are the task(s) in your list:";
    public static final String EMPTY_LIST = "(empty)";

    /**.
     * For markCommand
     */
    public static final String MARK_MESSAGE_TOP = "Nice! I have marked this task as completed!";
    public static final String UNMARK_MESSAGE_TOP = "Ok! I have marked this task for you as uncompleted!";
    public static final String ICON_UNCOMPLETED = "( )";
    public static final String ICON_COMPLETED = "(X)";

    /**.
     * For reset
     */
    public static final String RESET_MESSAGE = "All modules and tasks have been removed.";

    /**.
     * For command result
     */
    public static final String ARRAYLIST_RESULT = "ArrayList";
    public static final String STRING_RESULT = "String";

    /**.
     * For exceptions
     */
    public static final String ERROR_NO_SUCH_MODULE = "Sorry, no such module exists ._.";
    public static final String ERROR_NO_SUCH_TASK = "Sorry, no such task exists ._.";
    public static final String ERROR_PARSE_FAILED = "This parse failed 0_0";
    public static final String ERROR_UNKNOWN_COMMAND = "Sorry, I don't understand the following command:";
    public static final String ERROR_UNSUPPORTED_RESULT_TYPE = "Sorry, I don't understand the result format:";

    /**.
     * For parsers
     */
    public static final String TASK_NAME = "taskName";
    public static final String TASK_DESCRIPTION = "taskDescription";
    public static final String TASK_WORKING_TIME = "estimatedWorkingTime";
    public static final String TASK_MODULE = "taskModule";
    public static final String MODULE_CODE = "moduleCode";
    public static final String MODULE_DESCRIPTION = "moduleDescription";
    public static final String TASK_NUMBER = "taskNumber";
    public static final String FLAG = "flag";
    public static final String TASK_INDEX = "taskIndex";
    public static final String COMPLETED_FLAG = "/c";
    public static final String UNCOMPLETED_FLAG = "/u";
    public static final String ARGUMENT = "arguments";
    public static final String COMMAND_WORD = "commandWord";
    public static final String EXIT_COMMAND_WORD = "exit";
    public static final String ADD_COMMAND_WORD = "add";
    public static final String DELETE_COMMAND_WORD = "del";
    public static final String LIST_COMMAND_WORD = "list";
    public static final String MARK_COMMAND_WORD = "mark";
    public static final String RESET_COMMAND_WORD = "reset";

    /**.
     * General Strings
     */
    public static final String STRING = "String";
    public static final String INDENT = "    ";
    public static final String NULL_STRING = "";
    public static final String LS = System.lineSeparator();
    public static final String LINE = "____________________________________________________________";
}
