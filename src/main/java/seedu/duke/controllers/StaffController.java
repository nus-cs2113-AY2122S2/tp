package seedu.duke.controllers;

import seedu.duke.entities.Dish;
import seedu.duke.manager.StaffManager;
import seedu.duke.entities.Staff;

import java.util.Scanner;

public class StaffController extends Controller {
    private static final String[] CHOICES = {
        "Exit Staff Menu", "Find Staff", "Add Staff"
    };
    private final StaffManager staffManager;

    /**
     * Creates StaffController which controls StaffManager.
     *
     * @param scanner Scanner.
     * @param staffManager StaffManager that the controller controls.
     */
    public StaffController(Scanner scanner, StaffManager staffManager) {
        super(CHOICES, scanner);
        this.staffManager = staffManager;
    }

    @Override
    protected boolean optionSwitcher(int choice) {
        switch (choice) {
        case 1:
            Staff staff = findStaff();
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

    public Staff findStaff() {
        System.out.println("Finding staff...");
        System.out.print("ID of staff: ");
        int staffId = scanner.nextInt();
        scanner.nextLine();
        Staff targetStaff = staffManager.findByStaffId(staffId, true);
        return targetStaff;
    }

    public void addStaff() {
        System.out.println("Adding new staff...");
        System.out.print("ID of staff: ");
        final int staffId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name of staff: ");
        final String staffName = scanner.nextLine();
        System.out.println("Position of staff: ");
        final String position = scanner.nextLine();
        System.out.println("Salary of staff: ");
        final double salary = scanner.nextDouble();
        scanner.nextLine();
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
