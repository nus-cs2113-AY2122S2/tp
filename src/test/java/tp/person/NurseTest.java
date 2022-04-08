package tp.person;

import org.junit.jupiter.api.Test;
import tp.person.Nurse;


import static org.junit.jupiter.api.Assertions.assertEquals;

class NurseTest {
    private final Nurse nurse = new Nurse("1234", "john",
            "12341234", "3600@gmail.com", "Junior", 10);

    @Test
    void testToString() {
        assertEquals("* NURSE " + "[1234] || Name: john "
                + "|| Contact No.: 12341234 || Email: 3600@gmail.com "
                + "|| Title: Junior || Ward: 10", nurse.toString());
    }
}