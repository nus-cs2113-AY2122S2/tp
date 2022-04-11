package seedu.allonus.contacts.entry;

/**
 * Describes the Faculty object.
 * A subclass of Field.
 */
public class Faculty extends Field {

    /**
     * Initializes a new Faculty object.
     *
     * @param field Content of the Faculty field.
     */
    public Faculty(String field) {
        super(field);
    }

    @Override
    public boolean isValidField() {
        String currVal = this.field;
        return !currVal.isBlank();
    }

}
