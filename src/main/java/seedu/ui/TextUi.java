package seedu.ui;

import seedu.command.CommandResult;
import seedu.equipment.Equipment;

/**
 * Text UI for the application, referenced from
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
 */
public class TextUi {
    private final static String LOGO = "                  _                            _"
            + "                                               \n"
            + "                  (_)                          | |                                              \n"
            + "   ___  __ _ _   _ _ _ __  _ __ ___   ___ _ __ | |_ _ __ ___   __ _ _ __   __ _  __ _  ___ _ __ \n"
            + "  / _ \\/ _` | | | | | '_ \\| '_ ` _ \\ / _ \\ '_ \\| __| '_ ` _ \\ / _` "
            + "| '_ \\ / _` |/ _` |/ _ \\ '__|\n"
            + " |  __/ (_| | |_| | | |_) | | | | | |  __/ | | | |_| | | | | | (_| | | | | (_| | (_| |  __/ |   \n"
            + "  \\___|\\__, |\\__,_|_| .__/|_| |_| |_|\\___|_| "
            + "|_|\\__|_| |_| |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   \n"
            + "          | |       | |                                                          __/ |          \n"
            + "          |_|       |_|                                                         |___/";

    public void showWelcomeMessage() {
        System.out.println("Hello from" + System.lineSeparator() + LOGO);
        System.out.println("What do you want to do? (ENTER help TO SEE WHAT YOU CAN DO)");
    }

    /**
     * Shows the result of command execution to user.
     * @param result CommandResult returned from the execution of a command.
     */
    public void showResultToUser(CommandResult result) {
        System.out.println(result.getResultToShow());
        int indexShown = 1;
        if  (result.getRelevantEquipment() != null) {
            for (Equipment equipment : result.getRelevantEquipment()) {
                System.out.print(indexShown + ". ");
                System.out.println(equipment);
                indexShown++;
            }
        }
    }

}
