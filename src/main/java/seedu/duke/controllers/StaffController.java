package seedu.duke.controllers;

import seedu.duke.entities.Staff;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.StaffManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffController extends Controller {
    private static final String[] CHOICES = {
        "Exit Staff Menu", "Find Staff", "Add Staff"
    };
    private final StaffManager staffManager;
    private static Logger logger = Logger.getLogger("StaffController");

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
        case 1:
            findStaff();
            break;
        case 2:
            addStaff();
            break;
        case 3:
            deleteStaff();
            break;
        case 0:
            logger.log(Level.INFO, "Exiting staff Menu");
            System.out.println("Exiting Staff Menu...");
            return true;
        default:
            System.out.println("Unknown choice!");
            break;
        }
        System.out.println("Now in Staff Menu.");
        System.out.println(this);
        return false;
    }

    /**
     * Find staff by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void findStaff() throws OperationTerminationException {
        logger.log(Level.INFO, "Finding staff");
        System.out.println("Finding staff...");
        int staffId = InputParser.getInteger("ID of staff: ");
        staffManager.findByStaffId(staffId, true);
    }

    /**
     * Add staff.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void addStaff() throws OperationTerminationException {
        logger.log(Level.INFO, "Adding staff");
        System.out.println("Adding new staff...");
        final int staffId = InputParser.getInteger("ID of staff: ");
        final String staffName = InputParser.getString("Name of staff: ");
        final String position = InputParser.getString("Position of staff: ");
        final double salary = InputParser.getDouble("Salary of staff: ");
        staffManager.addStaff(staffId, staffName, position, salary);
    }

    /**
     * Delete staff by ID.
     *
     * @throws OperationTerminationException When user inputs terminator.
     */
    private void deleteStaff() throws OperationTerminationException {
        logger.log(Level.INFO, "Deleting staff");
        System.out.println("Deleting staff...");
        final int staffId = InputParser.getInteger("ID of staff: ");
        staffManager.deleteByStaffId(staffId);
    }

    /**
     * Override takeControl to print welcome message.
     */
    @Override
    public void takeControl() {
        System.out.println("You are entering the Staff Menu...");
        super.takeControl();
    }
}
