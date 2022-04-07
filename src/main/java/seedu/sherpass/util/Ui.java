package seedu.sherpass.util;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.timetable.Timetable;

import java.util.Scanner;

import static seedu.sherpass.constant.Message.ERROR_PREFIX;
import static seedu.sherpass.constant.Message.GOODBYE_MESSAGE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_ONE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_TWO;
import static seedu.sherpass.constant.Message.CLEAR_NO_EXPIRED_TASK_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_NO_COMPLETED_TASK_MESSAGE;

public class Ui {
    private static final Scanner in = new Scanner(System.in);
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";
    private static final String LS = System.lineSeparator();

    private static final String LOGO = "  ____  _\n"
            + " / ___|| |__   ___ _ __ "
            + "_ __   __ _ ___ ___\n"
            + " \\___ \\| '_ \\ / _ \\ '__| "
            + "'_ \\ / _` / __/ __|\n"
            + "  ___) | | | |  __/ |  "
            + "| |_) | (_| \\__ \\__ \\\n"
            + " |____/|_| |_|\\___|_|  "
            + "| .__/ \\__,_|___/___/\n"
            + "                       "
            + "|_|";



    /**
     * Returns any user input entered by the user through
     * the program's terminal. User Input is also trimmed to
     * remove white spaces.
     *
     * @return User input
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Returns a boolean value to see if there is any more
     * user inputs.
     *
     * @return Returns true if there is still user inputs. Method may
     *         be blocked while waiting for user inputs and scanner does
     *         not continue scanning for inputs when it is waiting.
     */
    public boolean hasInput() {
        return in.hasNext();
    }

    /**
     * Prints a partition line.
     */
    public void showLine() {
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints a message from the program to the user.
     * Can be in the form of exceptions that have occurred,
     * requests for inputs, command acknowledgement and so on.
     *
     * @param message Message to show to the user.
     */
    public void showToUser(String... message) {
        for (String s : message) {
            System.out.println(s);
        }
    }

    private void showReminderMessage(TaskList taskList, Ui ui) {
        int pendingTaskCount = taskList.getPendingTasksCount();
        if (pendingTaskCount > 0) {
            ui.showToUser("You have " + pendingTaskCount + " pending task(s).\n"
                    + "Head over to the study session with 'study' to complete them.");
        }
    }

    /**
     * Prints a welcome message to greet the user.
     * Shows the user the timetable and the number of pending tasks.
     *
     * @param taskList List of task in array representation
     * @param ui User interface
     */
    public void showWelcomeMessage(TaskList taskList, Ui ui) {
        String welcomeMessage = PARTITION_LINE + LS + WELCOME_MESSAGE_ONE
                + LOGO + LS + PARTITION_LINE + LS + WELCOME_MESSAGE_TWO;
        showToUser(welcomeMessage);
        Timetable.showTodaySchedule(taskList, ui);
        showReminderMessage(taskList, ui);
        showLine();
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }


    /**
     * Returns a string of repeated characters.
     *
     * @param character The character to print.
     * @param number The number of times to print that character.
     * @return Returns a concatenated string of repeated characters.
     */
    public String getRepeatedCharacters(String character, long number) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < number; i++) {
            output.append(character);
        }
        return output.toString();
    }

    public void showError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public boolean readYesNoCommand(String message) {
        System.out.println(message);
        while (true) {
            String input = readCommand();
            showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                return true;
            } else if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                return false;
            }
            showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            showLine();
        }
    }

    public void showClearRemoveTask(int oldTaskListSize, int newTaskListSize) {
        showToUser("Done! " + (oldTaskListSize - newTaskListSize) + " task(s) have been removed.",
                "Now you have " + newTaskListSize + " task(s) in the list.");
    }

    public void showClearFailToRemoveTask(String selection) {
        if (selection.equals("expired")) {
            showToUser(CLEAR_NO_EXPIRED_TASK_MESSAGE);
        }
        if (selection.equals("done")) {
            showToUser(CLEAR_NO_COMPLETED_TASK_MESSAGE);
        }
    }
}
