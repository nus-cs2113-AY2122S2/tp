package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.StaffManager;
import seedu.duke.loggers.MainLogger;

public class StaffController extends Controller {
    private static final String[] CHOICES = {
        "Exit Staff Menu", "Print Staff", "Find Staff", "Add Staff", "Delete Staff"
    };
    private final StaffManager staffManager;

    /**
     * Creates StaffController which controls StaffManager.
     */
    public StaffController() {
        super(CHOICES);
        this.staffManager = new StaffManager();
    }

    @Override
    protected boolean optionSwitcher(int choice) throws OperationTerminationException {
        switch (choice) {
        case 0:
            MainLogger.logInfo(this, "Exiting Staff Menu");
            System.out.println("Exiting Staff Menu...");
            try {
                staffManager.saveData();
            } catch (Exception e) {
                System.out.println("There was an error saving Staff data!\n");
                MainLogger.logWarning(this, "Error saving Staff data!");
            }
            return true;
        case 1:
            printStaff();
            break;
        case 2:
            findStaff();
            break;
        case 3:
            addStaff();
            break;
        case 4:
            deleteStaff();
            break;
        default:
            System.out.println("Unknown choice!");
            break;
        }
        System.out.println(this);
        return false;
    }

    /**
     * Prints out every staff in the records.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void printStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Printing staff");
        try {
            staffManager.printStaff();
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
    }

    /**
     * Find staff by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void findStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Finding staff");
        System.out.println("Finding staff...");
        int staffId = InputParser.getInteger("ID of staff: ");
        try{
            staffManager.findByStaffId(staffId, true);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * Add staff.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void addStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Adding staff");
        System.out.println("Adding new staff...");
        final int staffId = InputParser.getInteger("ID of staff: ");
        final String staffName = InputParser.getString("Name of staff: ");
        final String position = InputParser.getString("Position of staff: ");
        final double salary = InputParser.getDouble("Salary of staff: ");
        try{
            staffManager.addStaff(staffId, staffName, position, salary);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * Delete staff by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void deleteStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Deleting staff");
        System.out.println("Deleting staff...");
        final int staffId = InputParser.getInteger("ID of staff: ");
        try {
            staffManager.deleteByStaffId(staffId);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * Override takeControl to print welcome message.
     */
    @Override
    public void takeControl() {
        System.out.println("Entering Staff Menu...\n");
        super.takeControl();
    }
}