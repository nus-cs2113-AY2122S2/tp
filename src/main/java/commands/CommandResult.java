package commands;

import records.Record;

import java.util.List;

public class CommandResult {
    /** The feedback message to be shown to the user. Contains a description of the execution result. */
    private final String feedbackToUser;

    /** The list of records that was produced by the command. */
    private final List<Record> relevantRecords;

    /**
     * Constructs a <code>CommandResult</code> object.
     *
     * @param feedbackToUser Message to display to user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantRecords = null;
    }

    /**
     * Constructs a <code>CommandResult</code> object.
     *
     * @param feedbackToUser Message to display to user
     * @param relevantRecords List of records to display to user
     */
    public CommandResult(String feedbackToUser, List<Record> relevantRecords) {
        this.feedbackToUser = feedbackToUser;
        this.relevantRecords = relevantRecords;
    }

    /**
     * Returns a list of records that was produced by the command, if any.
     */
    public List<Record> getRelevantRecords() {
        return relevantRecords;
    }

    /**
     * @return Message to display to user
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
