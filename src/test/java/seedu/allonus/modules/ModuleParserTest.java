package seedu.allonus.modules;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.allonus.modules.exceptions.ModuleCodeException;
import seedu.allonus.modules.exceptions.ModuleCategoryException;
import seedu.allonus.modules.exceptions.ModuleDayException;
import seedu.allonus.modules.exceptions.ModuleTimeException;
import seedu.allonus.modules.exceptions.InvalidFindInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModuleParserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    StudyManager studyManager;
    ModuleParser moduleParser;

    private static final String DIVIDER = "---------------------------------------------------"
            + System.lineSeparator();

    private Module cs2113;

    private static final String validUserInput = "add m/CS2113 c/lec d/Friday t/4:00 pm-6:00 pm";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void setUpTests() {
        studyManager = new StudyManager();
        moduleParser = new ModuleParser();
        studyManager.getModulesList().clear();
        outContent.reset();
    }

    @Test
    public void testAddModuleParser() {
        cs2113 = new Module("CS2113", "Lecture", "Friday", "4:00 pm-6:00 pm");
        Module outputModule = moduleParser.addModuleParser(validUserInput);
        String outputAdd = "";
        assertEquals(outputAdd, outContent.toString());
        assertTrue(cs2113.isEqualTo(outputModule));

        outContent.reset();
        String invalidUserInput1 = "add";
        outputAdd = DIVIDER
                + "Please ensure that your input follows the form:" + System.lineSeparator()
                + "add m/CS2113 c/lec d/Thursday t/2:00 pm-4:00 pm" + System.lineSeparator()
                + DIVIDER;
        moduleParser.addModuleParser(invalidUserInput1);
        assertEquals(outputAdd,outContent.toString());

        outContent.reset();
        String invalidUserInput2 = "add m/-=- c/lec d/Friday t/4:00 pm-6:00 pm";
        outputAdd = DIVIDER
                + "Your module code must be an alphanumeric parameter!" + System.lineSeparator()
                + DIVIDER;
        moduleParser.addModuleParser(invalidUserInput2);
        assertEquals(outputAdd,outContent.toString());

        outContent.reset();
        String invalidUserInput3 = "add m/CS2113 c/packaged d/Friday t/4:00 pm-6:00 pm";
        outputAdd = DIVIDER + "Category has to be one of lec, tut, lab or exam" + System.lineSeparator() + DIVIDER;
        moduleParser.addModuleParser(invalidUserInput3);
        assertEquals(outputAdd,outContent.toString());

        outContent.reset();
        String invalidUserInput4 = "add m/CS2113 c/lec d/dday t/4:00 pm-6:00 pm";
        outputAdd = DIVIDER
                + "You have entered an invalid day of the week" + System.lineSeparator()
                + DIVIDER + DIVIDER
                + "Accepted module day inputs are either a day of the week or a valid date of type DD-MM-YYYY"
                + System.lineSeparator() + DIVIDER;
        moduleParser.addModuleParser(invalidUserInput4);
        assertEquals(outputAdd,outContent.toString());

        outContent.reset();
        String invalidUserInput5 = "add m/CS2113 c/lec d/12122022 t/4:00 pm-6:00 pm";
        outputAdd = DIVIDER + "You have entered an invalid date" + System.lineSeparator()
                + DIVIDER + DIVIDER
                + "Accepted module day inputs are either a day of the week or a valid date of type DD-MM-YYYY"
                + System.lineSeparator() + DIVIDER;
        moduleParser.addModuleParser(invalidUserInput5);
        assertEquals(outputAdd,outContent.toString());

        outContent.reset();
        String invalidUserInput6 = "add m/CS2113 c/lec d/Thursday t/4 pm-6 pm";
        outputAdd = DIVIDER + "Accepted module time slot input is a valid timeslot of type HH:MM am/pm - HH:MM am/pm"
                + System.lineSeparator() + DIVIDER;
        moduleParser.addModuleParser(invalidUserInput6);
        assertEquals(outputAdd,outContent.toString());

        //Add for missing input parameters
    }

    @Test
    public void testValidateModuleCode() {
        ModuleCodeException thrown = assertThrows(ModuleCodeException.class, () -> {
            moduleParser.validateModuleCode("-=!@#$%");
            moduleParser.validateModuleCode("cs!");
            moduleParser.validateModuleCode("123-=");
            moduleParser.validateModuleCode("");
        });

        assertEquals("Your module code must be an alphanumeric parameter!", thrown.getMessage());

        assertDoesNotThrow(() -> {
            String output = moduleParser.validateModuleCode("CS2113");
            assertEquals(output, "CS2113");

            output = moduleParser.validateModuleCode("EE4204");
            assertEquals(output, "EE4204");

            output = moduleParser.validateModuleCode("cs2113");
            assertEquals(output, "cs2113");
        });
    }

    @Test
    public void testValidateModuleCategory() {
        ModuleCategoryException thrown = assertThrows(ModuleCategoryException.class, () -> {
            moduleParser.validateModuleCategory("tutorial");
            moduleParser.validateModuleCategory("packaged");
            moduleParser.validateModuleCategory("123");
            moduleParser.validateModuleCategory("EXamination");

        });

        assertEquals("Category has to be one of lec, tut, lab or exam", thrown.getMessage());

        assertDoesNotThrow(() -> {
            String output = moduleParser.validateModuleCategory("lec");
            assertEquals(output, "Lecture");

            output = moduleParser.validateModuleCategory("tut");
            assertEquals(output, "Tutorial");

            output = moduleParser.validateModuleCategory("lab");
            assertEquals(output, "Laboratory");

            output = moduleParser.validateModuleCategory("exam");
            assertEquals(output, "Exam");
        });
    }

    @Test
    public void testValidateModuleDay() {
        ModuleDayException thrown = assertThrows(ModuleDayException.class, () -> {
            moduleParser.validateModuleDay("Anyday");
            moduleParser.validateModuleDay("MOnDay");
        });

        assertEquals("Accepted module day inputs are either a day of the week or"
                + " a valid date of type DD-MM-YYYY", thrown.getMessage());

        ModuleDayException thrownDate = assertThrows(ModuleDayException.class, () -> {
            moduleParser.validateModuleDay("12");
            moduleParser.validateModuleDay("12232022");
            moduleParser.validateModuleDay("23/08/22");
            moduleParser.validateModuleDay("23/08/2022");
            moduleParser.validateModuleDay("33/33/2099");
        });

        assertEquals("Accepted module day inputs are either a day of the week or "
                + "a valid date of type DD-MM-YYYY", thrownDate.getMessage());

        assertDoesNotThrow(() -> {
            String output = moduleParser.validateModuleDay("Monday");
            assertEquals(output, "Monday");

            output = moduleParser.validateModuleDay("12-12-2022");
            assertEquals(output, "12-12-2022");

            output = moduleParser.validateModuleDay("Sunday");
            assertEquals(output, "Sunday");

            output = moduleParser.validateModuleDay("31-12-1999");
            assertEquals(output, "31-12-1999");
        });
    }

    @Test
    public void testValidateModuleTime() {
        ModuleTimeException thrown = assertThrows(ModuleTimeException.class, () -> {
            moduleParser.validateModuleTime("2 pm");
            moduleParser.validateModuleTime("text");
            moduleParser.validateModuleTime("`131323`");
            moduleParser.validateModuleTime("2 pm-4 pm");
            moduleParser.validateModuleTime("24:00 pm-65:00 pm");

        });

        assertEquals("Accepted module time slot input is a valid timeslot of type HH:MM am/pm - HH:MM am/pm",
                thrown.getMessage());

        assertDoesNotThrow(() -> {
            String output = moduleParser.validateModuleTime("2:00 pm-4:00 pm");
            assertEquals(output, "2:00 pm-4:00 pm");

            output = moduleParser.validateModuleTime("2:00 pm-4:00 pm");
            assertEquals(output, "2:00 pm-4:00 pm");
        });
    }

    @Test
    public void testValidateFindQuery() {
        InvalidFindInputException thrown = assertThrows(InvalidFindInputException.class, () -> {
            moduleParser.validateFindQuery("find :");
            moduleParser.validateFindQuery("find [");
            moduleParser.validateFindQuery("find -");
        });

        assertEquals("You have entered a special character. Please refine your search query!",
                thrown.getMessage());

        InvalidFindInputException thrownMissingQuery = assertThrows(InvalidFindInputException.class, () -> {
            moduleParser.validateFindQuery("find  ");
        });

        assertEquals("You have not entered a search keyword to find modules!",
                thrownMissingQuery.getMessage());


        assertDoesNotThrow(() -> {
            String output = moduleParser.validateFindQuery("find cs");
            assertEquals(output, "cs");

            output = moduleParser.validateFindQuery("find 2:00 pm");
            assertEquals(output, "2:00 pm");
        });
    }
}
