package tp.person;

public class Doctor extends tp.person.Person {
    protected boolean isOnDuty;
    protected boolean assignedWard;
    protected String department;
    protected String wardNumber;

    public Doctor(String id, String name, String phoneNumber, String email, String department, String wardNumber) {
        super(id, name, phoneNumber, email);
        isOnDuty = false;
        assignedWard = true;
        this.department = department;
        this.wardNumber = wardNumber;
    }

    public Doctor(String id, String name, String phoneNumber, String email,String department) {
        super(id, name, phoneNumber, email);
        isOnDuty = false;
        assignedWard = false;
        this.department = department;
    }

    public void markOnDuty() {
        isOnDuty = true;
    }

    public void markOnLeave() {
        isOnDuty = false;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    @Override
    public String toString() {
        return "* DOCTOR " + super.toString() + " || Department: " + department + " || Ward: " + wardNumber;
    }

}
