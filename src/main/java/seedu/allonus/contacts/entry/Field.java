package seedu.allonus.contacts.entry;

/**
 * Describes the Field object; it is an abstract class
 * that is the superclass of Name, Faculty, Email, and Description.
 */
public abstract class Field {

    protected String field;

    /**
     * Initializes a new Field object.
     *
     * @param field Content of field.
     */
    public Field(String field) {
        this.field = field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public abstract boolean isValidField();

    @Override
    public String toString() {
        return field;
    }
}
