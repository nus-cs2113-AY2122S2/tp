package seedu.splitlah.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvalidFormatExceptionTest {

    private InvalidFormatException invalidFormatExceptionWithMessage;
    private InvalidFormatException invalidFormatExceptionWithMessageWithCause;
    private InvalidFormatException invalidFormatExceptionWithoutMessageWithoutCause;

    @BeforeEach
    public void setUp() {
        this.invalidFormatExceptionWithMessage = new InvalidFormatException("message");
        this.invalidFormatExceptionWithMessageWithCause = new InvalidFormatException("message", new Throwable("cause"));
        this.invalidFormatExceptionWithoutMessageWithoutCause = new InvalidFormatException();
    }

    @Test
    public void getMessage_withMessage_returnsMessage() {
        assertEquals("message", invalidFormatExceptionWithMessage.getMessage());
    }

    @Test
    public void getCause_withMessage_doesNotReturnCause() {
        Object cause = invalidFormatExceptionWithMessage.getCause();
        assertNull(cause);
    }

    @Test
    public void getMessage_withMessageWithCause_returnsMessage() {
        assertEquals("message", invalidFormatExceptionWithMessageWithCause.getMessage());
    }

    @Test
    public void getCause_withMessageWithCause_returnsCause() {
        assertEquals(new Throwable("cause").getMessage(),
                invalidFormatExceptionWithMessageWithCause.getCause().getMessage());
        assertEquals(new Throwable("cause").getCause(),
                invalidFormatExceptionWithMessageWithCause.getCause().getCause());
    }

    @Test
    public void getMessage_withoutMessageWithoutCause_doesNotReturnMessage() {
        String message = invalidFormatExceptionWithoutMessageWithoutCause.getMessage();
        assertNull(message);
    }

    @Test
    public void getCause_withoutMessageWithoutCause_doesNotReturnCause() {
        Throwable cause = invalidFormatExceptionWithoutMessageWithoutCause.getCause();
        assertNull(cause);
    }
}