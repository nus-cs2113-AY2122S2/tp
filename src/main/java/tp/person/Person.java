package tp.person;

public class Person {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String email;

    public Person(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setPerson(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void editName(String name) {
        this.name = name;
    }

    public void editPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void editEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "[" + id + "] || Name: " + name
                       + " || Contact No.: " + phoneNumber + " || Email: " + email;
    }
}
