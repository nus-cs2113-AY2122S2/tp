package tp.person;

public class Patient extends Person {
    public Patient(String id, String name, String phoneNumber, String email) {
        super(id, name, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "* PATIENT " + super.toString();
    }
}
