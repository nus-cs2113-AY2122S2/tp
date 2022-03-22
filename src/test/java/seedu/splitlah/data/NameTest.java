package seedu.splitlah.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.splitlah.exceptions.InvalidDataException;

public class NameTest {

    @Test
    public void constructor_nameHasNumbers_throwsException() throws InvalidDataException {
        assertThrows(InvalidDataException.class, () -> new Name("sam99"));
    }

    @Test
    public void constructor_nameHasSymbols_throwsException() throws InvalidDataException {
        assertThrows(InvalidDataException.class, () -> new Name("s@m9.9"));
    }
}
