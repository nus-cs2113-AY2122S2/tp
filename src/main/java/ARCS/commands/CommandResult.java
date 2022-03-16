package ARCS.commands;

import ARCS.data.Route;

import java.util.ArrayList;

public class CommandResult {
    private String feedbackToUser;
    private ArrayList<String> routesInfo = null;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public CommandResult(String feedbackToUser, ArrayList<String> routesInfo) {
        this.feedbackToUser = feedbackToUser;
        this.routesInfo = routesInfo;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public ArrayList<String> getRoutesInfo() {
        return routesInfo;
    }
}
