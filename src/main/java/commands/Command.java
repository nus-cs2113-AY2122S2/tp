package commands;

import common.Messages;

import manager.LimitManager;
import manager.RecordManager;

import records.Record;

import java.util.List;

/**
 * Represents an executable command.
 */
public class Command {
    protected RecordManager recordMgr;
    protected LimitManager limitMgr;

    public Command() {}
    
    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of records.
     *
     * @param recordsDisplayed Used to generate summary
     * @return Summary message for records displayed
     */
    protected static String getMessageForRecordListSummary(List<Record> recordsDisplayed) {
        return String.format(Messages.MESSAGE_RECORD_LISTED_OVERVIEW, recordsDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(RecordManager recordMgr) {
        this.recordMgr = recordMgr;
    }

    /**
     * Supplies the limit the command with operate with.
     *
     * @param limitMgr The manager of the limit
     */
    public void setLimitManager(LimitManager limitMgr) {
        this.limitMgr = limitMgr;
    }
}
