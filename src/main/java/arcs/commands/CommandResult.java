package arcs.commands;

import java.util.ArrayList;

public class CommandResult {
    private String feedbackToUser;
    private ArrayList<String> commandInfo = null;
    private String commandInfoString;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
    
    /**
     * Gets the feedback from the command result.
     * @return
     */
    public CommandResult(String feedbackToUser, ArrayList<String> commandInfo) {
        this.feedbackToUser = feedbackToUser;
        this.commandInfo = commandInfo;
    }

    public CommandResult(String feedbackToUser, String commandInfoString) {
        this.feedbackToUser = feedbackToUser;
        this.commandInfoString = commandInfoString;
    }

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

    public String getCommandInfoString() {
        return commandInfoString;
    }
}
