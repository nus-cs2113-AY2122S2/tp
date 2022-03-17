package arcs;

import arcs.commands.CommandResult;
import arcs.data.RouteManager;
import arcs.data.menu.MenuItemList;
import arcs.parser.Parser;
import arcs.storage.RouteFileManager;
import arcs.ui.MainUi;
import arcs.ui.MenuUi;
import arcs.ui.Ui;
import arcs.data.menu.MenuItem;
import arcs.commands.Command;
import arcs.commands.ExitCommand;
import arcs.data.exception.ArcsException;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

    private MainUi mainUi;
    private MenuUi menuUi;
    private MenuItemList menuItemList;
    private Ui ui;
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
        parser = new Parser();
        mainUi = new MainUi(parser);
        loadData();
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItemList = new MenuItemList(menuItems);

    }

    public void run() {
        mainUi.displayWelcomeMessage();
        Command command = new ExitCommand(mainUi);
        while (!command.isExit()) {
            try {
                if (command.isAtMainMenu()) {
                    command = parser.parseMainMenuInput(mainUi.readCommand(), mainUi, menuItemList,
                            routeManager);
                }
                //command.execute();
                CommandResult result = command.execute();
                if (result != null) {
                    mainUi.showResultToUser(result);
                }
            } catch (ArcsException e) {
                mainUi.displayMessage(e.getMessage());
            } finally {
                mainUi.printLineDivider();
            }
        }
        saveData();
    }

    /*public void run() {
        ui = new Ui();
        parser = new Parser();
        loadData();
        Command command;
        ui.showWelcomeMessage();


        do {
            ui.showDivider();
            String userCommandText = ui.getUserCommand();
            command = parser.parseCommand(userCommandText);
            command.setData(routeManager);
            CommandResult result = command.execute();
            ui.showResultToUser(result);
        } while (!command.isExit());

        saveData();
        ui.showExitMessage();
    }*/

    public void loadData() {
        routeFileManager = new RouteFileManager();
        try {
            routeManager = new RouteManager(routeFileManager.loadData());
        } catch (IOException e) {
            mainUi.showToUser(e.getMessage());
            routeManager = new RouteManager();
        }
    }

    public void saveData() {
        try {
            routeFileManager.saveData(routeManager.getAllRoutes());
        } catch (IOException e) {
            mainUi.showToUser(e.getMessage());
        }
    }
}
