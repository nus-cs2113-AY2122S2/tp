package seedu.duke.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    static final Logger logger = Logger.getLogger("CommmandTest");

    @BeforeEach
    void printDetails(TestInfo testInfo) {
        logger.info("Now testing ->" + testInfo.getDisplayName());
    }

}