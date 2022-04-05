package seedu.mindmymoney;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class MindMyMoneyTest {

    /**
     * Asserts if MMM is able to run and await for new line.
     * If a NoSuchElementException occurs, MM is trying to read a newline but unable to, hence ran successfully.
     */
    @Test
    public void mindMyMoney_runAndInputNewLine_expectedException() {
        assertThrows(NoSuchElementException.class,
            () -> new MindMyMoney().run());

    }
}
