package seedu.planitarium.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class PersonTest {
    private final String VALID_NAME = "Alice";
    private final int VALID_AMOUNT = 1000;
    private final int INVALID_INDEX = -10;

    @Test
    public void addIncome_invalidDescription_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.addIncome(null, VALID_AMOUNT);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }
}
