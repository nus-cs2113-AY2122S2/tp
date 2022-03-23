package seedu.duke.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import seedu.duke.exception.HalpmiException;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ParserTest {

    static final Logger logger = Logger.getLogger("ParserTest");

    @BeforeEach
    void printDetails(TestInfo testInfo) {
        logger.info("Now testing -> " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("validateMedicine method in Parser Class")
    void validateMedicineTest() {
        String[] userInputArray = {"paracetamol", "500", "2023-02-02", "Headaches", "500"};
        String userInput = "paracetamol,500,2023-02-02,Headaches,500";
        assertTrue(Validator.validateMedicine(userInputArray));
    }

    @Test
    @DisplayName("parseAddMedicine method in Parser Class")
    void parseAddMedicineTest() throws HalpmiException {
        String userInput = "paracetamol,500,2023-02-02,Headaches,500";
        String[] userInputArray = {"paracetamol", "500", "2023-02-02", "Headaches", "500"};
        assertArrayEquals(userInputArray, Parser.parseAddMedicine(userInput));
    }

    @Test
    @DisplayName("parseAddPatient method in Parser Class")
    void addPatientTest_userInputFormat_expectFormatCorrectlyValidated() throws HalpmiException {
        String userInput = "S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15";
        String[] expectedOutput = {"S1234567A", "John Doe", "23", "M", "10 Baker Street",
            "1999-12-31", "2021-02-15"};
        assertArrayEquals(expectedOutput, Parser.parseAddPatient(userInput));
    }
}
