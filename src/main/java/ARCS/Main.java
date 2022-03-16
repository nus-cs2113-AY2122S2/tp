package ARCS;

import ARCS.ui.Ui;


public class Main {
    private Ui ui;

    /**
     * Main entry-point for the ARCS application.
     */
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        ui = new Ui();
        ui.showWelcomeMessage();

        ui.showDivider();
        String userCommandText = ui.getUserCommand();
        ui.showResultToUser(userCommandText);
        ui.showDivider();

    }
}
