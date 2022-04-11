package seedu.allonus.contacts.entry;

/**
 * Describes the Name object.
 * A subclass of Field.
 */
public class Name extends Field {

    /**
     * Initializes a new Name object.
     *
     * @param field Content of the Name field.
     */
    public Name(String field) {
        super(field);
    }

    @Override
    public boolean isValidField() {
        String currVal = this.field;
        return !currVal.isBlank();
    }
}
