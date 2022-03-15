package seedu.allonus.contacts;

public class Contact {
    private String name;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
