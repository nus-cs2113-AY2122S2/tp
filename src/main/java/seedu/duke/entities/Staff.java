package seedu.duke.entities;

public class Staff {

    private int staffId;
    private String staffName;
    private String position;
    private double salary;

    /**
     * Create a Staff with id, name, position and salary.
     *
     * @param staffId    ID of the Staff.
     * @param staffName  Name of the Staff.
     * @param position   Job position of the Staff.
     * @param salary     Salary of the Staff.
     */
    public Staff(int staffId, String staffName, String position, double salary) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.position = position;
        this.salary = salary;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        if (staffId <= 0) {
            System.out.println("Staff ID cannot be zero or negative.");
            return;
        }
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        if (!isValidName(staffName)) {
            System.out.println("Staff name cannot be null.");
            return;
        }
        this.staffName = staffName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (!isValidName(position)) {
            System.out.println("Staff name cannot be null.");
            return;
        }
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("Salary cannot be zero or negative.");
            return;
        }
        this.salary = salary;
    }

    private static boolean isValidName(String name) {
        return !(name == null || name == "");
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-15s | %s", this.getStaffId(), this.getStaffName(), this.getPosition());
    }
}
