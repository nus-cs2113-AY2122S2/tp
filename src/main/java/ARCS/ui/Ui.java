package ARCS.ui;

import ARCS.commands.CommandResult;
import ARCS.common.Messages;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "-------------------------------------------------";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showWelcomeMessage() {
        System.out.println(Messages.WELCOME);
    }

    public void showExitMessage() {
        System.out.println(Messages.BYE);
    }

    public String getUserCommand() {
        String fullInputLine = sc.nextLine();
        fullInputLine = fullInputLine.trim();
        return fullInputLine;
    }

    public void showResultToUser(String result) {
        System.out.println(result);
    }

    public void showResultToUser(CommandResult result) {
        System.out.println(result.getFeedbackToUser());
        // Show routes information if it exists
        ArrayList<String> routesInfo = result.getRoutesInfo();
        if (routesInfo != null) {
            for (int i = 1; i <= routesInfo.size(); i++) {
                System.out.println(i + ".");
                System.out.println(routesInfo.get(i-1));
            }
        }
    }
}
