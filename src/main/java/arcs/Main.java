package arcs;

import arcs.commands.CommandResult;
import arcs.data.menuitems.MenuItemManager;
import arcs.data.RouteManager;
import arcs.parser.Parser;
import arcs.storage.RouteFileManager;
import arcs.commands.Command;
import arcs.ui.MainUi;

import java.io.IOException;


public class Main {

    private RouteManager routeManager;
    private MenuItemManager menuItemManager;
    private MainUi mainUi;
    private RouteFileManager routeFileManager;
    /**
     * Parser object.
     */
    private final Parser parser;

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
            System.out.println("Im here 1");
            command.setData(routeManager, menuItemManager);
            System.out.println("Im here 2");
            CommandResult result = command.execute();
            System.out.println("Im here 3");
            mainUi.displayResultToUser(result);
            mainUi.displayLineDivider();
            mainUi.displayGetNextUserCommand();
        } while (!command.isExit());

        saveData();
        mainUi.displayExitMessage();
    }

    public void loadData() {

        try {
            routeManager = new RouteManager(routeFileManager.loadData());
            menuItemManager = new MenuItemManager();
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
