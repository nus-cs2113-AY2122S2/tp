package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.ui.TextUi;

public class CommandResult {
    private String commandResultType = TextUi.STRING;
    private Object resultString;
    private ArrayList<String> resultArrayList;
    private String startWords = TextUi.NULL_STRING;
    private String endWords = TextUi.NULL_STRING;

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
        case TextUi.STRING_RESULT:
            return resultString.toString();
        case TextUi.ARRAYLIST_RESULT:
            String result = TextUi.NULL_STRING;
            if (!startWords.equals(TextUi.NULL_STRING)) {
                result = startWords + "\n";
            }
            for (int i = 0; i < resultArrayList.size(); i++) {
                result += String.format("%s. %s\n", i + 1, resultArrayList.get(i).toString());
            }
            if (!endWords.equals(TextUi.NULL_STRING)) {
                result = startWords + "\n";
            }
            return result;
        default:
            throw new UnsupportedOperationException();
        }
    }


}
