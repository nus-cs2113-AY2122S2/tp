package seedu.duke.assets;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DuplicateEntryException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentListTest {
    /*
    @Test
    void checkEmpty_viewByCriteria_expectNoAppointmentsFound() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AppointmentList testAppointmentList = new AppointmentList();
        String expectedOutput = "HalpMi: There are no appointments." + System.lineSeparator();
        testAppointmentList.view("patient name, dummy");
        assertEquals(expectedOutput, outContent.toString());
    }
     */

    @Test
    void checkFind_viewByCriteria_expectFoundAppointment() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AppointmentList testAppointmentList = new AppointmentList();
        String[] testParameters = {"ID1","S1234567A","John","S7654321Q","Ben","2023-01-01","Headache"};
        try {
            testAppointmentList.add(testParameters);
        } catch (DuplicateEntryException e) {
            e.toString();
        }
        testAppointmentList.view("patient name, John");
        String expectedOutput = "HalpMi: 1. Patient: John (S1234567A)" + System.lineSeparator()
                + "        Doctor: Ben (S7654321Q)" + System.lineSeparator()
                + "        Appointment date: 2023-01-01" + System.lineSeparator()
                + "        Appointment details: Headache" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
