package arcs;

import arcs.commands.CommandResult;
import arcs.data.customer.CustomerManager;
import arcs.data.mealreservation.MealReservationManager;
import arcs.data.route.RouteManager;
import arcs.data.flightbooking.FlightBookingManager;
import arcs.data.menuitems.MenuItemManager;
import arcs.data.staff.StaffManager;
import arcs.parser.Parser;
import arcs.storage.*;
import arcs.commands.Command;
import arcs.ui.MainUi;

import java.io.IOException;


public class Main {

    private RouteManager routeManager;
    private MenuItemManager menuItemManager;
    private MenuItemFileManager menuItemFileManager;
    private MainUi mainUi;
    private RouteFileManager routeFileManager;
    private FlightBookingManager flightBookingManager;
    private FlightBookingFileManager flightBookingFileManager;
    private CustomerManager customerManager;
    private CustomerFileManager customerFileManager;
    private StaffManager staffManager;
    private MealReservationManager mealReservationManager;
    private MealReservationFileManager mealReservationFileManager;
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
        customerFileManager = new CustomerFileManager();
        flightBookingFileManager = new FlightBookingFileManager();
        menuItemFileManager = new MenuItemFileManager();
        mealReservationFileManager = new MealReservationFileManager();
        loadData();
    }

    public void run() {
        Command command;
        mainUi.displayWelcomeMessage();

        do {
            mainUi.displayGetNextUserCommand();
            String userCommandText = mainUi.getUserCommand();
            command = parser.parseCommand(userCommandText);
            command.setData(routeManager, flightBookingManager,
                    menuItemManager, customerManager, staffManager,
                    mealReservationManager);
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
            menuItemManager = new MenuItemManager(menuItemFileManager.loadData());
            customerManager = new CustomerManager(customerFileManager.loadData());
            flightBookingManager = new FlightBookingManager(flightBookingFileManager.loadData());
            mealReservationManager = new MealReservationManager(mealReservationFileManager.loadData(), flightBookingManager);

        } catch (IOException e) {
            mainUi.displayMessages(e.getMessage());
            routeManager = new RouteManager();
            customerManager = new CustomerManager();
            flightBookingManager = new FlightBookingManager();
            menuItemManager = new MenuItemManager();
            mealReservationManager = new MealReservationManager();
        }
    }

    public void saveData() {
        try {
            routeFileManager.saveData(routeManager.getAllRoutes());
            customerFileManager.saveData(customerManager.getAllCustomers());
            flightBookingFileManager.saveData(flightBookingManager.getAllFlightBookings());
            menuItemFileManager.saveData(menuItemManager.getAllMenuItems());
            mealReservationFileManager.saveData(mealReservationManager.getAllMealReservations());
        } catch (IOException e) {
            mainUi.displayMessages(e.getMessage());
        }
    }
}
