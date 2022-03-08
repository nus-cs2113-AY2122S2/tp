package seedu.duke.commands;

import java.util.ArrayList;

public class CommandResult {

    private static final String ARRAYLIST_RESULT = "ArrayList";
    private static final String NULL_STRING = "";
    private static final String STRING_RESULT = "String";

    private String commandResultType = "String";
    private Object resultString;
    private ArrayList<String> resultArrayList;
    private String startWords = "";
    private String endWords = "";

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
        case STRING_RESULT:
            return resultString.toString();
        case ARRAYLIST_RESULT:
            String result = NULL_STRING;
            if (!startWords.equals(NULL_STRING)) {
                result = startWords + "\n";
            }
            for (int i = 0; i < resultArrayList.size(); i++) {
                result += String.format("%s. %s\n", i + 1, resultArrayList.get(i).toString());
            }
            if (!endWords.equals(NULL_STRING)) {
                result = startWords + "\n";
            }
            return result;
        default:
            throw new UnsupportedOperationException();
        }
    }


}
