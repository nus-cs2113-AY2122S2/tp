package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

/**
 * Represents a name. Names must contain only alphabetical characters.
 * Removes leading and trailing whitespace from supplied names.
 *
 * @author Saurav
 */
public class Name {

    private final String name;

    public Name(String name) {
        this.name = name.strip();
    }

    public String getNameAsString() {
        return name;
    }

    public static boolean validateName(String name) {
        return name.strip().matches("[a-zA-Z]+");
    }
}
