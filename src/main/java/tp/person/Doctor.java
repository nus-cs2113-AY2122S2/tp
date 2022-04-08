package tp.person;

public class Doctor extends tp.person.Person {
    protected boolean isOnDuty;
    protected boolean assignedWard;
    protected String department;
    protected int wardNumber;


    public Doctor(String id, String name, String phoneNumber, String email, String department, int wardNumber) {
        super(id, name, phoneNumber, email);
        isOnDuty = false;
        assignedWard = true;
        this.department = department;
        this.wardNumber = wardNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void markOnDuty() {
        isOnDuty = true;
    }

    public void markOnLeave() {
        isOnDuty = false;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    @Override
    public String toString() {
        return "* DOCTOR " + super.toString() + " || Department: " + department + " || Ward: " + wardNumber;
    }

}
