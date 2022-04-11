package arcs.ui;

import arcs.commands.CommandResult;
import arcs.common.Messages;

import java.util.ArrayList;
import java.util.Scanner;

public class MainUi {

    /**
     * Scanner for user input.
     */
    static final Scanner scannerIn = new Scanner(System.in);


    /**
     * Shows message to user.
     *
     * @param message Message to show.
     */
    public void displayMessages(String message) {
        System.out.println(message);
    }

    public void displayMessages(ArrayList<String> messages) {
        for (String message: messages) {
            System.out.println(message);
        }
    }

    public void displayGetNextUserCommand() {
        System.out.println(Messages.REQUEST_USER_COMMAND);
    }

    /**
     * Prints welcome message.
     */
    public void displayWelcomeMessage() {
        System.out.println(Messages.WELCOME);
    }

    public void displayLineDivider() {
        System.out.println(Messages.lineDivider);
    }

    /**
     * Prints exit message when user exits the app.
     */
    public void displayExitMessage() {
        System.out.println("Your data has been saved!");
        System.out.println(Messages.BYE);
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String getUserCommand() {
        String fullInputLine = scannerIn.nextLine();
        fullInputLine = fullInputLine.trim();
        return fullInputLine;
    }

    public void displayResultToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
        // Show information if it exists
        showCommandInfo(result);
        if (!(result.getCommandInfoString() == null) && !result.getCommandInfoString().isEmpty()) {
            System.out.println(result.getCommandInfoString());
        }
    }

    private void showCommandInfo(CommandResult commandResult) {
        ArrayList<String> commandInfo = commandResult.getCommandInfo();
        if (commandInfo != null) {
            for (int i = 1; i <= commandInfo.size(); i++) {
                System.out.print(i + ".");
                System.out.println(commandInfo.get(i - 1));
            }
        }
    }
}
