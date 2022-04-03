package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.StaffManager;
import seedu.duke.loggers.MainLogger;

public class StaffController extends Controller {
    private static final String[] CHOICES = {
        "Exit Staff Menu", "Print Staff", "Find Staff", "Add Staff", "Delete Staff", "Edit Staff"
    };
    private final StaffManager staffManager;

    /**
     * Creates StaffController which controls StaffManager.
     */
    public StaffController() {
        super(CHOICES);
        this.staffManager = StaffManager.getInstance();
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
        System.out.println("Printing staff...");
        try {
            staffManager.printStaff();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
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
        try {
            staffManager.findByStaffId(staffId, true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
        int staffId;
        while (true) {
            staffId = InputParser.getInteger("ID of staff: ");
            if (checkNoStaffClash(staffId)) {
                break;
            } else {
                System.out.println("Staff with the same ID already exists, use another ID...");
            }
        }
        final String staffName = InputParser.getString("Name of staff: ");
        final String position = InputParser.getString("Position of staff: ");
        final double salary = InputParser.getDouble("Salary of staff: ");
        try {
            staffManager.addStaff(staffId, staffName, position, salary);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
        if (checkStaffEmpty()) {
            System.out.println("No staff in records to delete yet!");
            return;
        }
        int staffId;
        while (true) {
            staffId = InputParser.getInteger("ID of staff to delete: ");
            if (!checkNoStaffClash(staffId)) {
                break;
            } else {
                System.out.println("Failed to find staff with matching ID, please try again...");
            }
        }
        try {
            staffManager.deleteByStaffId(staffId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Deleting staff");
        System.out.println("Editing staff...");
        if (checkStaffEmpty()) {
            System.out.println("No staff in records to edit yet!");
            return;
        }
        int staffId;
        while (true) {
            staffId = InputParser.getInteger("ID of staff to edit: ");
            if (!checkNoStaffClash(staffId)) {
                break;
            } else {
                System.out.println("Failed to find staff with matching ID, please try again...");
            }
        }
        int choice = 0;
        do {
            if (choice < 1 || choice > 4) {
                System.out.println("Input out of range, please input a number from 1 to 4...");
            }
            choice = InputParser.getInteger("1. ID of staff: \n" +
                    "2. Name of staff\n" +
                    "3. Position of staff\n" +
                    "4. Salary of staff\n" +
                    "Select field to edit: ");
        } while (choice < 1 || choice > 4);
        String change = InputParser.getString("New " );
        try {
            staffManager.deleteByStaffId(staffId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    boolean checkStaffEmpty() {
        return staffManager.getStaff().size() == 0;
    }

    boolean checkNoStaffClash(int staffId) {
        if (staffManager.findByStaffId(staffId, false) == null) {
            return true;
        }
        return true;
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