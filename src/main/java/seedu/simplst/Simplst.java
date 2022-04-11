package seedu.simplst;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Simplst {
    public static void main(String[] args) {
        UserInterface ui;
        Display.hello();
        Warehouse w = new Warehouse(0);
        Boolean status = w.restoreWarehouseState();
        if (status) {
            Display.stateRestored();
        } else {
            Display.newLogin();
            w = new Warehouse(1000);
        }
        ui = new UserInterface(w);
        ui.run();
        status = w.saveWarehouseState();
        if (status) {
            Display.stateSaved();
        } else {
            Display.stateNotSaved();
        }
        Display.bye();
    }
}
