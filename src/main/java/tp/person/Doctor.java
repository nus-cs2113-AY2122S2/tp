package tp.person;

public class Doctor extends tp.person.Person {
    protected boolean isOnDuty;
    protected boolean assignedWard;
    protected int wardNumber;

    public Doctor(String id, String name, String phoneNumber, String email, int wardNumber) {
        super(id, name, phoneNumber, email);
        isOnDuty = false;
        assignedWard = true;
        this.wardNumber = wardNumber;
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
        return "* DOCTOR " + super.toString() + " || Ward: " + wardNumber;
    }

}
