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
     * @throws IllegalArgumentException Check if arguments do not fit the requirements.
     */
    public Staff(int staffId, String staffName, String position, double salary) throws IllegalArgumentException{
        if (staffId <= 0) {
            throw new IllegalArgumentException("Staff ID cannot be zero or negative.");
        }
        if (!isValidName(staffName)) {
            throw new IllegalArgumentException("Staff name cannot be null.");
        }
        if (!isValidName(position)) {
            throw new IllegalArgumentException("Position cannot be null or empty.");
        }
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary cannot be zero or negative.");
        }
        this.staffId = staffId;
        this.staffName = staffName;
        this.position = position;
        this.salary = salary;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) throws IllegalArgumentException{
        if (staffId <= 0) {
            throw new IllegalArgumentException("Staff ID cannot be zero or negative.");
        }
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) throws IllegalArgumentException {
        if (!isValidName(staffName)) {
            throw new IllegalArgumentException("Staff name cannot be null.");
        }
        this.staffName = staffName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) throws IllegalArgumentException {
        if (!isValidName(position)) {
            throw new IllegalArgumentException("Staff name cannot be null.");
        }
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws IllegalArgumentException {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary cannot be zero or negative.");
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
