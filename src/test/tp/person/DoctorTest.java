package tp.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    private final Doctor doctor = new Doctor("1234", "john",
            "12341234", "3600@gmail.com");
    @Test
    void testToString() {
        assertEquals("* DOCTOR " + "[1234] || Name: john " +
                             "|| Contact No.: 12341234 || Email: 3600@gmail.com", doctor.toString());
    }
}