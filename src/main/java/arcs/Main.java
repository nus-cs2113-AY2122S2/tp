package arcs;

import arcs.commands.CommandResult;
import arcs.data.RouteManager;
import arcs.parser.Parser;
import arcs.storage.RouteFileManager;
import arcs.commands.Command;
import arcs.ui.MainUi;

import java.io.IOException;


public class Main {
    private MainUi mainUi;
    private RouteManager routeManager;

    /**
     * Parser object.
     */
    private final Parser parser;

    private RouteFileManager routeFileManager;

    /**
     * Main entry-point for the ARCS application.
     */
    public static void main(String[] args) {
        new Main().run();
    }

    public Main() {
        // Initialize ui, logic and storage classes
        mainUi = new MainUi();
        parser = new Parser();
        routeFileManager = new RouteFileManager();
        loadData();

    }


    public void run() {
        Command command;
        mainUi.displayWelcomeMessage();

        do {
            String userCommandText = mainUi.getUserCommand();
            command = parser.parseCommand(userCommandText);
            command.setData(routeManager);
            CommandResult result = command.execute();
            mainUi.displayResultToUser(result);
            mainUi.displayLineDivider();
        } while (!command.isExit());

        saveData();
        mainUi.displayExitMessage();
    }

    public void loadData() {

        try {
            routeManager = new RouteManager(routeFileManager.loadData());
        } catch (IOException e) {
            mainUi.displayMessages(e.getMessage());
            routeManager = new RouteManager();
        }
    }

    public void saveData() {
        try {
            routeFileManager.saveData(routeManager.getAllRoutes());
        } catch (IOException e) {
            mainUi.displayMessages(e.getMessage());
        }
    }
}
