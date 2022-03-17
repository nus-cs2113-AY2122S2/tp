import static org.junit.jupiter.api.Assertions.*;
class ProfileTest {
  
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        String sessionTwoArgs = "session /create /n Family gathering /d 20-02-2022  /pl Eves Mallory";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);
        Command createSessionTwo = Parser.getCommand(sessionTwoArgs);
        createSessionTwo.run(manager);
    }

    /**
     * Checks if method returns true when a session name exists within the list of session.
     */
    @Test
    public void hasSessionName_inputContainsExistingSessionName_true() {
        String sessionNameToTest = "Class outing";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertEquals(true, isExists);
    }

    /**
     * Checks if method returns false when a session name does not exist within the list of session.
     */
    @Test
    public void hasSessionName_inputContainsNonExistingSessionName_false() {
        String sessionNameToTest = "School gathering";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertEquals(false, isExists);
    }
}