package seedu.allonus.contacts.entry;

/**
 * Describes the Email object.
 * A subclass of Field.
 */
public class Email extends Field {

    /**
     * Initializes a new Email object.
     *
     * @param field Content of the Email field.
     */
    public Email(String field) {
        super(field);
    }

    @Override
    public boolean isValidField() {
        String currVal = this.field;
        return !currVal.isBlank();
    }

    public boolean isValidFormat() {
        String currVal = this.field;
        String emailFormatLenient = "(.+?)(@.+?)(\\.)(.+)";
        return currVal.matches(emailFormatLenient);
    }

}
