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
     */
    private void printStaff() {
        MainLogger.logInfo(this, "Printing staff");
        System.out.println("Printing staff...");
        if (checkStaffEmpty()) {
            System.out.println("No staff in records to print!");
            return;
        }
        staffManager.printStaff();
    }

    /**
     * Find staff by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void findStaff() throws OperationTerminationException {
        MainLogger.logInfo(this, "Finding staff");
        System.out.println("Finding staff...");
        if (checkStaffEmpty()) {
            System.out.println("No staff in records to find!");
            return;
        }
        try {
            while (true) {
                int staffId = InputParser.getInteger("ID of staff: ");
                Staff staff = staffManager.findByStaffId(staffId);
                if (staff != null) {
                    System.out.println("Staff found: \n" + staff);
                    break;
                } else {
                    System.out.println("Staff with ID " + staffId + " not found!");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
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
        int staffId = 0;
        try {
            while (true) {
                staffId = InputParser.getInteger("ID of staff: ");
                boolean staffNoClash = checkNoStaffClash(staffId);
                if (staffNoClash) {
                    break;
                } else {
                    System.out.println("Staff with the same ID already exists, use another ID...");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        final String staffName = InputParser.getString("Name of staff: ");
        final String position = InputParser.getString("Position of staff: ");
        double salary;
        salary = InputParser.getDouble("Salary of staff: ");
        try {
            staffManager.addStaff(staffId, staffName, position, salary);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
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
            System.out.println("No staff in records to delete!");
            return;
        }
        int staffId = 0;
        try {
            while (true) {
                staffId = InputParser.getInteger("ID of staff to delete: ");
                boolean staffNoClash = checkNoStaffClash(staffId);
                if (!staffNoClash) {
                    break;
                } else {
                    System.out.println("Failed to find staff with matching ID, please try again...");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        staffManager.deleteByStaffId(staffId);
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
            System.out.println("No staff in records to edit!");
            return;
        }
        int staffId;
        Staff staff = null;
        boolean staffNoClash;
        try {
            while (true) {
                staffId = InputParser.getInteger("ID of staff to edit: ");
                staffNoClash = checkNoStaffClash(staffId);
                if (!staffNoClash) {
                    staff = staffManager.findByStaffId(staffId);
                    break;
                } else {
                    System.out.println("Failed to find staff with matching ID, please try again...");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            editStaffByField(staff);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * Edit selected Staff's field.
     *
     * @param staff Staff to be edited.
     * @throws OperationTerminationException When user inputs terminator.
     * @throws IllegalArgumentException When ID or salary is zero or negative.
     */
    private void editStaffByField(Staff staff) throws OperationTerminationException, IllegalArgumentException {
        int choice;
        int staffId;
        boolean staffNoClash;
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
                editStaffId(staff);
                break;
            case 2:
                editStaffName(staff);
                break;
            case 3:
                editStaffPosition(staff);
                break;
            case 4:
                editStaffSalary(staff);
                break;
            default:
                System.out.println("Input out of range, please input a number from 0 to 4...");
            }
        }
    }

    private void editStaffId(Staff staff) throws OperationTerminationException, IllegalArgumentException {
        int staffId;
        boolean staffNoClash;
        while (true) {
            staffId = InputParser.getInteger("New ID of staff: ");
            staffNoClash = checkNoStaffClash(staffId);
            if (staffNoClash) {
                staff.setStaffId(staffId);
                System.out.println("ID successfully updated!");
                break;
            } else {
                System.out.println("Staff with the same ID already exists, use another ID...");
            }
        }
    }

    private void editStaffName(Staff staff) throws OperationTerminationException {
        final String staffName = InputParser.getString("New name of staff: ");
        staff.setStaffName(staffName);
        System.out.println("Name successfully updated!");
    }

    private void editStaffPosition(Staff staff) throws OperationTerminationException {
        final String position = InputParser.getString("New position of staff: ");
        staff.setPosition(position);
        System.out.println("Position successfully updated!");
    }

    private void editStaffSalary(Staff staff) throws OperationTerminationException, IllegalArgumentException {
        while (true) {
            double salary = InputParser.getDouble("New salary of staff: ");
            staff.setSalary(salary);
            System.out.println("Salary successfully updated!");
            break;
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
     * @return boolean expression if there is a clash in IDs, or if there is an exception.
     * @throws IllegalArgumentException If ID is zero or negative.
     */
    private boolean checkNoStaffClash(int staffId) throws IllegalArgumentException {
        if (staffManager.findByStaffId(staffId) == null) {
            return true;
        }
        return false;
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