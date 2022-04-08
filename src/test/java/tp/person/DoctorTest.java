package tp.person;

import org.junit.jupiter.api.Test;
import tp.person.Doctor;


import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorTest {
    private final Doctor doctor = new Doctor("1234", "john",
            "12341234", "3600@gmail.com",10);

    @Test
    void testToString() {
        assertEquals("* DOCTOR " + "[1234] || Name: john "
                             + "|| Contact No.: 12341234 || Email: 3600@gmail.com "
                             + "|| Department: General || Ward: 10", doctor.toString());
    }
}