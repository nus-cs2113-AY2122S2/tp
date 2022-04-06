package arcs.commands;

import java.util.ArrayList;

public class CommandResult {
    private String feedbackToUser;
    private ArrayList<String> commandInfo = null;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public CommandResult(String feedbackToUser, ArrayList<String> commandInfo) {
        this.feedbackToUser = feedbackToUser;
        this.commandInfo = commandInfo;
    }

    /**
     * Gets the feedback from the command result.
     * @return
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Gets additional command result information.
     * @return additional information of the command result.
     */
    public ArrayList<String> getCommandInfo() {
        return commandInfo;
    }
}
