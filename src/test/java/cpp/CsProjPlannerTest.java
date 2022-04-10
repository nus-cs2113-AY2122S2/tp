package cpp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

class CsProjPlannerTest {
    private final InputStream standardIn = System.in;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private ByteArrayInputStream testIn;

    private static final String FILEPATH = "./src/data/projectList.txt";
    private static final String SEPARATOR = "____________________________________________________________\n";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";
    private static final String WELCOME_MESSAGE = "\n   ___________ ____               _    ____  __\n"
            + "  / ____/ ___// __ \\_________    (_)  / __ \\/ /___ _____  ____  ___  _____\n"
            + " / /    \\__ \\/ /_/ / ___/ __ \\  / /  / /_/ / / __ `/ __ \\/ __ \\/ _ \\/ ___/\n"
            + "/ /___ ___/ / ____/ /  / /_/ / / /  / ____/ / /_/ / / / / / / /  __/ /\n"
            + "\\____//____/_/   /_/   \\____/_/ /  /_/   /_/\\__,_/_/ /_/_/ /_/\\___/_/\n"
            + "                           /___/\n"
            + "\nWelcome to CSProjPlanner\n\n"
            + "Enter \"help\" for available commands\n"
            + SEPARATOR + "What can I help you with?\n" + SEPARATOR;
    private static final String EXIT_MESSAGE = SEPARATOR + "Bye. Wish you have a nice day.\n" + SEPARATOR;
    private static final String HELP_NEXT = "What can I help you with next?\n" + SEPARATOR;
    private static final String UNKNOWN_COMMAND = SEPARATOR + "Execution result: Unknown Command.\n" + SEPARATOR
            + HELP_NEXT;
    private static final String HELP_MESSAGE = SEPARATOR + "Here are all the possible commands:\n"
            + "1. addproject [Project Name]\n"
            + "2. deleteproject [Project Name]\n"
            + "3. changegit [Project Index] [GitHub URL]\n"
            + "4. opengit [Project Name]\n"
            + "5. projdeadline [Project Index] [Date]\n"
            + "6. tododeadline [Project Index] [Todo Index] [Date]\n"
            + "7. todo [Project Index] [Description]\n"
            + "8. mark [Project Index] [Todo Index]\n"
            + "9. listprojects/listproject\n"
            + "10. addlanguage [Project Index] [Language]\n"
            + "11. listlanguages [Project Name]\n"
            + "12. view [Project Name]\n"
            + "13. exit\n" + SEPARATOR + HELP_NEXT;

    @BeforeEach
    public void setUp() {
        File saveFile = new File(FILEPATH);
        saveFile.delete();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(standardIn);
        System.setOut(standardOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testWelcomeAndExit() {
        provideInput(EXIT_COMMAND);
        CsProjPlanner.main(new String[0]);
        assertEquals(WELCOME_MESSAGE + EXIT_MESSAGE, outputStream.toString());
    }

    @Test
    void testInvalidCommand() {
        provideInput("blah\n" + EXIT_COMMAND);
        CsProjPlanner.main(new String[0]);
        assertEquals(WELCOME_MESSAGE + UNKNOWN_COMMAND + EXIT_MESSAGE, outputStream.toString());
    }

    @Test
    void testHelp() {
        provideInput(HELP_COMMAND + "\n" + EXIT_COMMAND);
        CsProjPlanner.main(new String[0]);
        assertEquals(WELCOME_MESSAGE + HELP_MESSAGE + EXIT_MESSAGE, outputStream.toString());
    }

    @Test
    void testDebug() {
        System.out.println("Testing");
        assertEquals("Testing\n", outputStream.toString());
    }
}
