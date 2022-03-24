package seedu.duke.entities;

import java.io.Serializable;

public class Staff implements Serializable {

    private int staffId;
    private String staffName;
    private String position;
    private double salary;

    /**
     * Create a Staff with id, name, position and salary.
     *
     * @param staffId   ID of the Staff.
     * @param staffName Name of the Staff.
     * @param position  Job position of the Staff.
     * @param salary    Salary of the Staff.
     */
    public Staff(int staffId, String staffName, String position, double salary) {
        setStaffId(staffId);
        setStaffName(staffName);
        setPosition(position);
        setSalary(salary);
    }

    private static boolean isValidName(String name) {
        return !(name == null || name.length() == 0);
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        if (staffId <= 0) {
            System.out.println("Staff ID cannot be zero or negative.");
            return;
        }
        assert staffId > 0 : "Staff ID should be more than 0.";
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
        assert isValidName(staffName) : "Staff name should not be null.";
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
        assert isValidName(position) : "Position should not be null.";
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
        assert salary > 0 : "Salary should be more than 0.";
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-15s | %s", this.getStaffId(), this.getStaffName(), this.getPosition());
    }
}
