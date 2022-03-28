package seedu.planitarium.global;

import java.util.ArrayList;

public class Help {
    public static ArrayList<String> CommandSummary;

    public Help() {
    }

    /**
     * Initialize an array to print help message
     */
    public static void initialiseHelp(){
        CommandSummary = new ArrayList<String>();
        CommandSummary.add(Constants.ADD_PERSON);
        CommandSummary.add(Constants.DELETE_PERSON);
        CommandSummary.add(Constants.ADD_INCOME);
        CommandSummary.add(Constants.DELETE_INCOME);
        CommandSummary.add(Constants.EDIT_INCOME);
        CommandSummary.add(Constants.ADD_EXPEND);
        CommandSummary.add(Constants.DELETE_EXPEND);
        CommandSummary.add(Constants.EDIT_EXPEND);
        CommandSummary.add(Constants.LIST);
        CommandSummary.add(Constants.LIST_BY_GROUP);
        CommandSummary.add(Constants.LISTCAT);
        CommandSummary.add(Constants.HELP);
        CommandSummary.add(Constants.EXIT);
    }
}
