package seedu.splitlah.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvalidDataExceptionTest {

    private InvalidDataException invalidDataExceptionWithMessage;
    private InvalidDataException invalidDataExceptionWithMessageWithCause;
    private InvalidDataException invalidDataExceptionWithoutCauseWithoutMessage;

    @BeforeEach
    public void setUp() {
        this.invalidDataExceptionWithMessage = new InvalidDataException("message");
        this.invalidDataExceptionWithMessageWithCause = new InvalidDataException("message", new Throwable("cause"));
    }

    @Test
    public void getMessage_withMessage_returnsMessage() {
        assertEquals("message", invalidDataExceptionWithMessage.getMessage());
    }

    @Test
    public void getCause_withMessage_doesNotReturnCause() {
        Object cause = invalidDataExceptionWithMessage.getCause();
        assertNull(cause);
    }

    @Test
    public void getMessage_withMessageWithCause_returnsMessage() {
        assertEquals("message", invalidDataExceptionWithMessageWithCause.getMessage());
    }

    @Test
    public void getCause_withMessageWithCause_returnsCause() {
        assertEquals(new Throwable("cause"), invalidDataExceptionWithMessageWithCause.getCause());
    }

    @Test
    public void getMessage_withoutMessageWithoutCause_doesNotReturnMessage() {
        String message = invalidDataExceptionWithoutCauseWithoutMessage.getMessage();
        assertNull(message);
    }

    @Test
    public void getCause_withoutMessageWithoutCause_doesNotReturnCause() {
        Throwable cause = invalidDataExceptionWithoutCauseWithoutMessage.getCause();
        assertNull(cause);
    }
}
