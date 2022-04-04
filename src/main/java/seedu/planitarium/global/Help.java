package seedu.planitarium.global;

import java.util.ArrayList;

public class Help {
    public static ArrayList<String> CommandSummary;

    public Help() {
    }

    /**
     * Initialize an array to print help message.
     */
    public static void initialiseHelp() {
        CommandSummary = new ArrayList<String>();
        CommandSummary.add(Constants.HELP);
        CommandSummary.add(Constants.ADD_PERSON);
        CommandSummary.add(Constants.DELETE_PERSON);
        CommandSummary.add(Constants.ADD_INCOME);
        CommandSummary.add(Constants.DELETE_INCOME);
        CommandSummary.add(Constants.EDIT_INCOME);
        CommandSummary.add(Constants.ADD_EXPEND);
        CommandSummary.add(Constants.DELETE_EXPEND);
        CommandSummary.add(Constants.EDIT_EXPEND);
        CommandSummary.add(Constants.OVERVIEW);
        CommandSummary.add(Constants.LIST);
        CommandSummary.add(Constants.LISTCAT);
        CommandSummary.add(Constants.FIND);
        CommandSummary.add(Constants.EXIT);
        CommandSummary.add(Constants.EMPTY_STRING);
        CommandSummary.add(Constants.PARAMETER_NAME);
        CommandSummary.add(Constants.PARAMETER_GID);
        CommandSummary.add(Constants.PARAMETER_UID);
        CommandSummary.add(Constants.PARAMETER_DESC);
        CommandSummary.add(Constants.PARAMETER_CID);
        CommandSummary.add(Constants.PARAMETER_INCOME);
        CommandSummary.add(Constants.PARAMETER_EXPENDITURE);
        CommandSummary.add(Constants.PARAMETER_RECORDS);
        CommandSummary.add(Constants.PARAMETER_RECURRING);
    }
}
