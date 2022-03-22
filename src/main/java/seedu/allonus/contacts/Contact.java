package seedu.allonus.contacts;

public class Contact {
    private String name;
    private String faculty;
    private String email;
    private String description;

    public Contact(String name, String faculty, String email, String description) {
        this.name = name;
        this.faculty = faculty;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
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
