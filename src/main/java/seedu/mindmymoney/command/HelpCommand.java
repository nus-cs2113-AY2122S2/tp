package seedu.mindmymoney.command;

/**
 * Represents the Help command. This class also serves as a dummy class to return when an invalid command is
 * received.
 */
public class HelpCommand extends Command {
    protected boolean isFromUser;

    public HelpCommand(boolean isFromUser) {
        this.isFromUser = isFromUser;
    }

    /**
     * Prints out the help page if the user requested for it. If not, it means an invalid command was received,
     * and prints out an error message.
     */
    public void executeCommand() {
        if (isFromUser) {
            String helpPage = "------------------------------------Help Page------------------------------------\n"
                    + "1. Listing all Expenditures: list\n"
                    + "2. Adding an Expenditure entry: add [DESCRIPTION] [AMOUNT]\n"
                    + "3. Updating an Expenditure entry: update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]\n"
                    + "4. Removing an Expenditure entry: delete [INDEX]\n"
                    + "5. Exiting the program: bye\n"
                    + "---------------------------------------------------------------------------------\n";

            System.out.println(helpPage);
        } else {
            System.out.println("Invalid command! Type \"help\" to see the list of supported commands"
                    + System.lineSeparator());
        }
    }
}
