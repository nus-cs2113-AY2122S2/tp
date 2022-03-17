package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.data.exception.ArcsException;
import seedu.duke.data.menu.MenuItem;
import seedu.duke.data.menu.MenuItemList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.MainUi;
import seedu.duke.ui.MenuUi;

import java.util.ArrayList;

/**
 * Represents the main control for ARCS
 */

public class Duke {
    /**
     * Logger object.
     */
    //public static Logger logger;

    /**
     * Parser object.
     */
    private final Parser parser;

    /**
     * Storage handler.
     */
    //private final Storage storage;

    /**
     * User interface.
     */
    private MainUi mainUi;
    private MenuUi menuUi;
    private MenuItemList menuItemList;
    /**
     * List of users.
     */
    //private UserList users;

    /**
     * List of courses.
     */
    //private CourseList courses;

    /**
     * Access date/time.
     */
    //private AccessDateTime accessDateTime;

    /**
     * Initializes ARCS.
     */
    public Duke() {
        //logger = Logger.getLogger(MyStars.class.getName());
        //logger.setLevel(Level.WARNING);
        parser = new Parser();
        mainUi = new MainUi(parser);
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItemList = new MenuItemList(menuItems);
        //storage = new Storage(parser);
        /*try {
            //load data here
        } catch (ArcsException e) {
            mainUi.displayMessage(e.getMessage());
        }*/
    }

    public static void main(String[] args) {
        new Duke().run();
    }


    public void run() {
        mainUi.displayWelcomeMessage();
        Command command = new ExitCommand(mainUi);
        while (!command.isDoneUsingApp()) {
            try {
                if (command.isIsAtMainMenu()) {
                    command = parser.parseMainMenuInput(mainUi.readCommand(), mainUi, menuItemList);
                }
                command.execute();
            } catch (ArcsException e) {
                mainUi.displayMessage(e.getMessage());
            } finally {
                mainUi.printLineDivider();
            }
        }
    }
}


/*    public void run() {
        ui.showLine();
        ui.showWelcome();
        Command command = new LogoutCommand(ui);
        while (!command.isExit()) {
            try {
                if (!command.isLogin()) {
                    command = new LoginCommand(ui, users, accessDateTime);
                } else if (command.getUser() instanceof Student) {
                    ui = new StudentUi();
                    command = parser.parseStudentInput(ui.readCommand(), users, (StudentUi) ui, courses, storage);
                } else if (command.getUser() instanceof Admin) {
                    ui = new AdminUi();
                    command = parser.parseAdminInput(ui.readCommand(), users, (AdminUi) ui, courses, storage,
                            accessDateTime);
                }
                command.execute();
            } catch (MyStarsException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}*/

