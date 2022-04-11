package seedu.simplst;

import util.exceptions.NullException;

import java.io.IOException;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Simplst {
    public static void main(String[] args) throws NullException, IOException {
        UserInterface ui;
        Display.hello();
        Warehouse w = new Warehouse(0);
        Boolean status = w.restoreWarehouseState();
        if (status) {
            Display.stateRestored();
        } else {
            Display.warehouseStateNotRestored();
            Display.newLogin();
            w = new Warehouse(1000);
        }
        ui = new UserInterface(w);
        ui.run();
        status = w.saveWarehouseState();
//        if (status) {
//            Display.stateSaved();
//        } else {
//            Display.stateNotSaved();
//        }
        Display.bye();
    }
}
