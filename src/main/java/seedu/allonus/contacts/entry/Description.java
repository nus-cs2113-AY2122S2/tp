package seedu.allonus.contacts.entry;

/**
 * Describes the Description object.
 * A subclass of Field.
 */
public class Description extends Field {

    /**
     * Initializes a new Description object.
     *
     * @param field Content of the Description field.
     */
    public Description(String field) {
        super(field);
    }

    @Override
    public boolean isValidField() {
        String currVal = this.field;
        return !currVal.isBlank();
    }

}
