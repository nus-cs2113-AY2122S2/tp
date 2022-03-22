package seedu.duke.assets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientListTest {
    @Test
    void checkPatientConstructorAndList_onePatientInput_OnePatientInList() {
        String testNric = "S1234567A";
        String testFullName = "Tan Wei Li";
        String testAge = "20";
        String testGender = "M";
        String testAddress = "14 Baker Street";
        String testDob = "1999-03-11";
        String testDateAdmission = "2022-01-01";
        String[] testAddPatientParameters = {testNric, testFullName,testAge, testGender, testAddress, testDob,
            testDateAdmission};
        PatientList testPatientList = new PatientList();
        testPatientList.add(testAddPatientParameters);
        assertEquals(1, testPatientList.getSize());
    }
}
