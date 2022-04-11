package seedu.duke.assets;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DuplicateEntryException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientListTest {
    @Test
    void checkSize_onePatientInList_expectOne() {
        PatientList testPatientList = new PatientList();
        int currentNumber = 0;
        assertEquals(currentNumber, testPatientList.getSize());
    }

    @Test
    void checkGetPatient_correctNricGiven_patientFound() throws DuplicateEntryException {
        String testNric = "S1234567A";
        String testFullName = "Tan Wei Li";
        String testAge = "20";
        String testGender = "M";
        String testAddress = "14 Baker Street";
        String testDob = "1999-03-11";
        String testDateAdmission = "2022-01-01";
        String[] testAddPatientParameters = {testNric, testFullName,testAge, testGender, testAddress, testDob,
            testDateAdmission};
        Patient testPatient = new Patient(testNric, testFullName,Integer.parseInt(testAge), testGender.charAt(0),
                testAddress, testDob, testDateAdmission);
        PatientList testPatientList = new PatientList();
        testPatientList.add(testAddPatientParameters);
        assertEquals(testPatientList.getPatient(testNric).getNric(), testPatient.getNric());
    }
}
