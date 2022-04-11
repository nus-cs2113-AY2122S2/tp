package seedu.allonus.contacts.entry;

/**
 * Describes the Contact object.
 */
public class Contact {

    private final Name name;
    private final Faculty faculty;
    private final Email email;
    private final Description description;

    /**
     * Initializes a new Contact object.
     *
     * @param name The Name object corresponding to this Contact object.
     * @param faculty The Faculty object corresponding to this Contact object.
     * @param email The Email object corresponding to this Contact object.
     * @param description The Description object corresponding to this Contact object.
     */
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

    @Override
    public String toString() {
        return "Name: " + getName()
                +  ", Faculty: " + getFaculty()
                + ", Email: " + getEmail()
                + ", Description: " + getDescription();
    }
}
