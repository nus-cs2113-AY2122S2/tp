package seedu.allonus.modules;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModuleCalendarReaderTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String DIVIDER = "---------------------------------------------------"
            + System.lineSeparator();

    ModuleCalendarReader moduleCalendarReader;

    @BeforeEach
    public void setUpTests() {
        moduleCalendarReader = new ModuleCalendarReader();
        outContent.reset();
        moduleCalendarReader.icsFilePath = "nusmods_calendar.ics";
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testReadIcsFile() {
        ArrayList<Module> icsModuleList = moduleCalendarReader.readIcsFile("nusmods_calendar.ics");
        assertNotNull(icsModuleList, "read ics returned null");

        outContent.reset();
        moduleCalendarReader.readIcsFile("wrong_filename.ics");
        String readIcsOutput = DIVIDER
                + "No such file found! Please ensure you have the correct name." + System.lineSeparator()
                + "Then place the file in the same directory as AllOnUs.jar." + System.lineSeparator()
                + DIVIDER;
        assertEquals(readIcsOutput, outContent.toString());
    }

    @Test
    public void parseIcsCalendar() {
        assertDoesNotThrow(() -> {
            moduleCalendarReader.icsFilePath = "wrong_filename.ics";
            moduleCalendarReader.parseIcsCalendar();
            String parseIcsOutput = "File wrong_filename.ics was not found" + System.lineSeparator();
            assertEquals(parseIcsOutput, outContent.toString());
        });
    }
}
