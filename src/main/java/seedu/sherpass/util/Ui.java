package seedu.sherpass.util;

import seedu.sherpass.task.TaskList;

import java.util.Scanner;

import static seedu.sherpass.constant.Message.GOODBYE_MESSAGE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_ONE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_TWO;


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

    /**
     * Prints a welcome message to greet the user.
     */
    public void showWelcomeMessage(TaskList tasklist, Ui ui) {
        String welcomeMessage = PARTITION_LINE + LS + WELCOME_MESSAGE_ONE
                + LOGO + LS + PARTITION_LINE + LS + WELCOME_MESSAGE_TWO;
        showToUser(welcomeMessage);
        //Timetable.showTodaySchedule(tasklist, ui);
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
}
