package commands;

import data.record.Record;

import java.util.List;

public class CommandResult {
    /** The feedback message to be shown to the user. Contains a description of the execution result */
    private final String feedbackToUser;

    /** The list of records that was produced by the command */
    private final List<Record> relevantRecords;


    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantRecords = null;
    }

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

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
