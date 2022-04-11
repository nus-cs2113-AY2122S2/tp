package seedu.allonus.contacts;

/**
 * Describes exceptions specific to invalid fields supplied to the Contacts Manager.
 */
public class InvalidContactField extends Exception {

    public InvalidContactField(String message) {
        super(message);
    }
}
