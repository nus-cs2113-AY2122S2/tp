package seedu.splitlah.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvalidFormatExceptionTest {

    private InvalidDataException invalidFormatExceptionWithMessage;
    private InvalidDataException invalidFormatExceptionWithMessageWithCause;
    private InvalidDataException invalidFormatExceptionWithoutMessageWithoutCause;

    @BeforeEach
    public void setUp() {
        this.invalidFormatExceptionWithMessage = new InvalidDataException("message");
        this.invalidFormatExceptionWithMessageWithCause = new InvalidDataException("message", new Throwable("cause"));
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
        assertEquals(new Throwable("cause"), invalidFormatExceptionWithMessageWithCause.getCause());
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