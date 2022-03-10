import static org.junit.jupiter.api.Assertions.*;
class SessionCreateCommandTest {
  
    Manager manager = new Manager();
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 2022-02-15 /pl Alice Bob";
        String sessionTwoArgs = "session /create /n Family gathering /d 2022-02-20  /pl Eves Mallory";
        Command createSessionOne = SessionCreateCommand.prepare(sessionOneArgs);
        createSessionOne.run(manager);
        Command createSessionTwo = SessionCreateCommand.prepare(sessionTwoArgs);
        createSessionTwo.run(manager);
    }
}