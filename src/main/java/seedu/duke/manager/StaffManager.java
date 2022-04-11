package seedu.duke.manager;


import seedu.duke.entities.Staff;
import seedu.duke.loggers.MainLogger;

import java.util.ArrayList;

/**
 * StaffManager is a class which stores/handles/manages many Staffs.
 */
public class StaffManager extends Manager {
    private static StaffManager singleton = null;
    private ArrayList<Staff> staffs;
    private static final String STORAGE_FILE = "staff.dat";

    /**
     * Create StaffManager and load Staffs.
     */
    private StaffManager() {
        super(STORAGE_FILE);
        try {
            this.loadData();
        } catch (Exception e) {
            MainLogger.logWarning(this, e.toString());
            MainLogger.logWarning(this, "Start with no staffs");
            staffs.clear();
        }
    }

    public static StaffManager getInstance() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new StaffManager();
        return singleton;
    }

    public static void resetInstance() {
        singleton = null;
    }

    @Override
    protected void loadData() throws Exception {
        this.staffs = new ArrayList<>();
        ArrayList<?> list = (ArrayList<?>) this.load();
        for (Object object : list) {
            this.loadStaff((Staff) object);
        }
    }

    @Override
    public void saveData() throws Exception {
        this.save(this.staffs);
    }

    public ArrayList<Staff> getStaff() {
        return staffs;
    }

    public void loadStaff(Staff staff) {
        staffs.add(staff);
    }

    /**
     * Print all the Staffs.
     */
    public void printStaff() {
        for (int i = 0; i < staffs.size(); i++) {
            System.out.println((i + 1) + ". " + staffs.get(i));
        }
    }

    /**
     * Add a Staff to staffs.
     *
     * @param staffId   ID of the Staff.
     * @param staffName Name of the Staff.
     * @param position  Job position of the Staff.
     * @param salary    Salary of the Staff.
     *
     * @throws IllegalArgumentException If salary is zero or negative.
     */
    public void addStaff(int staffId, String staffName, String position, double salary) throws IllegalArgumentException {
        staffs.add(new Staff(staffId, staffName, position, salary));
        MainLogger.logInfo(this, "Successful addition of staff");
    }

    /**
     * Find Staff from Staffs by ID.
     *
     * @param staffId  ID of the Staff.
     * @throws IllegalArgumentException If ID is zero or negative.
     */
    public Staff findByStaffId(int staffId) throws IllegalArgumentException {
        if (staffId <= 0) {
            MainLogger.logInfo(this, "Invalid input for ID.");
            throw new IllegalArgumentException("Staff ID cannot be zero or negative.");
        }
        for (Staff staff : staffs) {
            if (staffId == staff.getStaffId()) {
                MainLogger.logInfo(this, "Successful search for staff.");
                return staff;
            }
        }
        MainLogger.logInfo(this, "Unsuccessful search for staff.");
        return null;
    }

    /**
     * Delete Staff from Staffs by ID.
     *
     * @param staffId ID of the Staff
     */
    public void deleteByStaffId(int staffId) {
        Staff staff = findByStaffId(staffId);
        staffs.remove(staff);
        MainLogger.logInfo(this, "Successful deletion of staff.");
        System.out.println(staff + " had been deleted from our staff records.");
    }

    /**
     * Return the number of staffs in Staffs.
     *
     * @return the number of current staff.
     */
    public int getNumOfStaffs() {
        return staffs.size();
    }
}