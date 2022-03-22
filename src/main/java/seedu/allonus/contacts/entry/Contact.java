package seedu.allonus.contacts.entry;

public class Contact {

    private Name name;
    private Faculty faculty;
    private Email email;
    private Description description;

    public Contact(Name name, Faculty faculty, Email email, Description description) {
        this.name = name;
        this.faculty = faculty;
        this.email = email;
        this.description = description;
    }

    public Name getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Email getEmail() {
        return email;
    }

    public Description getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name.setField(name);
    }

    @Override
    public String toString() {
        String contactPrinted = "Name: " + getName()
                +  ", Faculty: " + getFaculty()
                + ", Email: " + getEmail()
                + ", Description: " + getDescription();
        return contactPrinted;
    }
}
