package seedu.duke.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    static final Logger logger = Logger.getLogger("ParserTest");

    @BeforeEach
    void printDetails(TestInfo testInfo) {
        logger.info("Now testing -> " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("validateMedicine method in Parser Class")
    void validateMedicineTest() {
        String[] userInputArray = {"paracetamol","500","2023-02-02","Headaches","500"};
        String userInput = "paracetamol,500,2023-02-02,Headaches,500";
        assertEquals(true,Parser.validateMedicine(userInputArray));
    }

    @Test
    @DisplayName("parseAddMedicine method in Parser Class")
    void parseAddMedicineTest() {
        String userInput = "paracetamol,500,2023-02-02,Headaches,500";
        String[] userInputArray = {"paracetamol","500","2023-02-02","Headaches","500"};
        assertArrayEquals(userInputArray,Parser.parseAddMedicine(userInput));
    }


}