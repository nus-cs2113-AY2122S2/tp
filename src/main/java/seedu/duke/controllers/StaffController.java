package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.StaffManager;
import seedu.duke.loggers.MainLogger;
import seedu.duke.entities.Staff;

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
        case 5:
            editStaff();
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
            Staff staff = staffManager.findByStaffId(staffId);
            if (staff != null) {
                System.out.println("Staff found: \n" + staff);
            } else {
                System.out.println("Staff with ID " + staffId + " not found!");
            }
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
            int staffNoClash = checkNoStaffClash(staffId);
            if (staffNoClash == 1) {
                break;
            } else if (staffNoClash == 0) {
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
            int staffNoClash = checkNoStaffClash(staffId);
            if (staffNoClash == 0) {
                break;
            } else if (staffNoClash == 1) {
                System.out.println("Failed to find staff with matching ID, please try again...");
            }
        }
        try {
            staffManager.deleteByStaffId(staffId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Edit staff by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void editStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Deleting staff");
        System.out.println("Editing staff...");
        if (checkStaffEmpty()) {
            System.out.println("No staff in records to edit yet!");
            return;
        }
        int staffId;
        Staff staff;
        int staffNoClash;
        while (true) {
            staffId = InputParser.getInteger("ID of staff to edit: ");
            staffNoClash = checkNoStaffClash(staffId);
            if (staffNoClash == 0) {
                staff = staffManager.findByStaffId(staffId);
                break;
            } else if (staffNoClash == 1) {
                System.out.println("Failed to find staff with matching ID, please try again...");
            }
        }
        int choice;
        while (true) {
            choice = InputParser.getInteger("0. Exit\n"
                    + "1. ID of staff\n"
                    + "2. Name of staff\n"
                    + "3. Position of staff\n"
                    + "4. Salary of staff\n"
                    + "Select field to edit: ");
            switch (choice) {
            case 0:
                return;
            case 1:
                while (true) {
                    staffId = InputParser.getInteger("New ID of staff: ");
                    staffNoClash = checkNoStaffClash(staffId);
                    if (staffNoClash == 1) {
                        staff.setStaffId(staffId);
                        break;
                    } else if (staffNoClash == 0) {
                        System.out.println("Staff with the same ID already exists, use another ID...");
                    }
                }
                break;
            case 2:
                final String staffName = InputParser.getString("New name of staff: ");
                staff.setStaffName(staffName);
                break;
            case 3:
                final String position = InputParser.getString("New position of staff: ");
                staff.setPosition(position);
                break;
            case 4:
                final double salary = InputParser.getDouble("New salary of staff: ");
                staff.setSalary(salary);
                break;
            default:
                System.out.println("Input out of range, please input a number from 1 to 5...");
            }
        }
    }

    /**
     * Check if staff records is empty.
     *
     * @return boolean expression if staff records is empty or not.
     */
    private boolean checkStaffEmpty() {
        return staffManager.getNumOfStaffs() == 0;
    }

    /**
     * Check if there is no clash between input ID and ID of existing staff.
     *
     * @param staffId ID of the staff.
     * @return integer expression if there is a clash in IDs, or if there is an exception.
     */
    private int checkNoStaffClash(int staffId) {
        try {
            if (staffManager.findByStaffId(staffId) == null) {
                return 1;
            }
            return 0;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
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