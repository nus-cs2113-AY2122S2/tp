package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.util.StringConstants;

public class CommandResult {
    private String commandResultType = StringConstants.STRING;
    private Object resultString;
    private ArrayList<String> resultArrayList;
    private String startWords = StringConstants.NULL_STRING;
    private String endWords = StringConstants.NULL_STRING;

    public CommandResult(String result) {
        this.resultString = result;
    }

    public CommandResult(String resultString, String commandResultType) {
        this.resultString = resultString;
        this.commandResultType = commandResultType;
    }

    public CommandResult(ArrayList<String> result, String commandResultType) {
        this.resultArrayList = result;
        this.commandResultType = commandResultType;
    }

    public void setStartWords(String startWords) {
        this.startWords = startWords;
    }


    public void setEndWords(String endWords) {
        this.startWords = endWords;
    }

    @Override
    public String toString() {
        switch (commandResultType) {
        case StringConstants.STRING_RESULT:
            return resultString.toString();
        case StringConstants.ARRAYLIST_RESULT:
            String result = StringConstants.NULL_STRING;
            if (!startWords.equals(StringConstants.NULL_STRING)) {
                result = startWords + "\n";
            }
            for (int i = 0; i < resultArrayList.size(); i++) {
                result += String.format("%s. %s\n", i + 1, resultArrayList.get(i).toString());
            }
            if (!endWords.equals(StringConstants.NULL_STRING)) {
                result = startWords + "\n";
            }
            return result;
        default:
            throw new UnsupportedOperationException();
        }
    }


}
