package tp.person;

public class Doctor extends Person {
    protected boolean isOnDuty;

    public Doctor(int id, String name, String phoneNumber, String email) {
        super(id, name, phoneNumber, email);
        isOnDuty = false;
    }

    public void markOnDuty() {
        isOnDuty = true;
    }

    public void markOnLeave() {
        isOnDuty = false;
    }

    @Override
    public String toString() {
        return "* DOCTOR " + super.toString();
    }
}
