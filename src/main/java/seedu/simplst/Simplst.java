package seedu.simplst;

import java.io.IOException;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Simplst {
    public static void main(String[] args) {
        UserInterface ui;
        Warehouse w = new Warehouse(0);
        Boolean status = w.restoreWarehouseState();
        if (status) {
            Display.stateRestored();
        } else {
            Display.warehouseStateNotRestored();
            Display.newLogin();
            w = new Warehouse(1000);
        }
        Display.hello();
        ui = new UserInterface(w);
        ui.run();
        try {
            w.saveWarehouseState();
        } catch (IOException e) {
            System.out.println("File not saved!");
        }
        Display.bye();
    }
}
