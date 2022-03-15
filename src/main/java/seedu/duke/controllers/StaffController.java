package seedu.duke.controllers;

import seedu.duke.entities.Staff;
import seedu.duke.exceptions.OperationTerminationException;
import seedu.duke.manager.StaffManager;

public class StaffController extends Controller {
    private static final String[] CHOICES = {
        "Exit Staff Menu", "Find Staff", "Add Staff"
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
        case 1:
            Staff staff = findStaff();
            System.out.println(staff);
            break;
        case 2:
            addStaff();
            break;
        case 0:
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

    public Staff findStaff() throws OperationTerminationException {
        System.out.println("Finding staff...");
        int staffId = InputParser.getInteger("ID of staff: ");
        return staffManager.findByStaffId(staffId, true);
    }

    public void addStaff() throws OperationTerminationException {
        System.out.println("Adding new staff...");
        final int staffId = InputParser.getInteger("ID of staff: ");
        final String staffName = InputParser.getString("Name of staff: ");
        final String position = InputParser.getString("Position of staff: ");
        final double salary = InputParser.getDouble("Salary of staff: ");
        staffManager.addStaff(staffId, staffName, position, salary);
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
