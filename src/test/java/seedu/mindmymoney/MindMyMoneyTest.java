package seedu.mindmymoney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mindmymoney.command.AddCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

class MindMyMoneyTest {

    /**
     * Asserts if MMM is able to run and await for new line.
     */
    @Test
    public void mindMyMoney_runAndInputNewLine_expectedException() {
        assertThrows(NoSuchElementException.class,
            () -> new MindMyMoney().run());
    }
}
