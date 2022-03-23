package seedu.duke.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.logging.Logger;

public class UiTest {
    static final Logger logger = Logger.getLogger("UiTest");

    @BeforeEach
    void printDetails(TestInfo testInfo) {
        logger.info("Now testing:");
    }
}
