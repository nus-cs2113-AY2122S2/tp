package tp.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author cczhouqi
class PatientTest {
    private final Patient patient = new Patient("1234", "john",
            "12341234", "3600@gmail.com", "symptom", "description");

    @Test
    void testToString() {
        assertEquals("* PATIENT " + "[1234] || Name: john "
                + "|| Contact No.: 12341234 || Email: 3600@gmail.com || Symptom: symptom"
                + " || Description: description", patient.toString());
    }
}