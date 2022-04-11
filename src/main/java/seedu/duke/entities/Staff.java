package seedu.duke.entities;

import seedu.duke.loggers.MainLogger;

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
    public Staff(int staffId, String staffName, String position, double salary) throws IllegalArgumentException {
        setStaffId(staffId);
        setStaffName(staffName);
        setPosition(position);
        setSalary(salary);
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) throws IllegalArgumentException {
        if (staffId <= 0) {
            throw new IllegalArgumentException("Staff ID cannot be zero or negative.");
        }
        assert staffId > 0 : "Staff ID should be more than 0.";
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) throws IllegalArgumentException {
        if (staffName.isEmpty()) {
            throw new IllegalArgumentException("Staff name cannot be null.");
        }
        assert !(staffName.isEmpty()) : "Staff name should not be null.";
        this.staffName = staffName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position.isEmpty()) {
            throw new IllegalArgumentException("Staff position cannot be null.");
        }
        assert !(position.isEmpty()) : "Position should not be null.";
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary cannot be zero or negative.");
        }
        assert salary > 0 : "Salary should be more than 0.";
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-15s | %-10s | $%.2f",
                this.getStaffId(), this.getStaffName(), this.getPosition(), this.getSalary());
    }
}
