package ARCS;

import ARCS.commands.Command;
import ARCS.commands.CommandResult;
import ARCS.data.RouteManager;
import ARCS.data.exception.ARCSException;
import ARCS.parser.Parser;
import ARCS.ui.Ui;



public class Main {
    private Ui ui;
    private RouteManager routeManager;
    private Parser parser;

    /**
     * Main entry-point for the ARCS application.
     */
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        ui = new Ui();
        routeManager = new RouteManager();
        parser = new Parser();
        Command command;
        ui.showWelcomeMessage();

        do {
            ui.showDivider();
            String userCommandText = ui.getUserCommand();
            command = parser.parseCommand(userCommandText);
            command.setData(routeManager);
            CommandResult result = command.execute();
            ui.showResultToUser(result);
//            try {
//                CommandResult result = command.execute();
//                ui.showResultToUser(result);
//            } catch (ARCSException e) {
//                ui.showToUser(e.getMessage());
//            }
        } while (!command.isExit());

        ui.showExitMessage();
    }
}
