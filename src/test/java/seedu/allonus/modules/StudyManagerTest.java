package seedu.allonus.modules;

import org.junit.jupiter.api.*;
import seedu.allonus.modules.exceptions.ModuleCategoryException;
import seedu.allonus.ui.TextUi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the working of StudyManager class.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyManagerTest {

    public static final String USER_INPUT_FOR_DELETE = "rm 1";
    private Module cs2113;
    private Module cs3244;
    private Module ee4204;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private final PrintStream originalOut = System.out;

    ArrayList<Module> testList = new ArrayList<>();
    StudyManager studyManager;

    /**
     * Creates three new module objects and adds them to the test module list.
     */
    @BeforeAll
    public void setUp() {
        cs2113 = new Module("CS2113", "Lecture", "Friday", "4:00pm-6:00pm");
        cs3244 = new Module("CS3244", "Tutorial", "Monday", "2:00pm-3:00pm");
        ee4204 = new Module("EE4204", "Exam", "Monday", "10:00am-12:00pm");

        testList.add(cs2113);
        testList.add(cs3244);
        testList.add(ee4204);

        studyManager = new StudyManager();
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Tests add module function of Study Manager class.
     * Ensures that the contents of the module list is equal to the test list.
     */
    @BeforeEach
    public void testAddModule() {

        studyManager.getModulesList().clear();
        String userInputCs2113 = "add m/CS2113 c/lec d/Friday t/4:00pm-6:00pm";
        String userInputCs3244 = "add m/CS3244 c/tut d/Monday t/2:00pm-3:00pm";
        String userInputEe4204 = "add m/EE4204 c/exam d/Monday t/10:00am-12:00pm";

        studyManager.addModule(userInputCs2113, false);
        studyManager.addModule(userInputCs3244, false);
        studyManager.addModule(userInputEe4204, false);

        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i).getModuleCode(), studyManager.getModulesList().get(i).getModuleCode());
            assertEquals(testList.get(i).getCategory(), studyManager.getModulesList().get(i).getCategory());
            assertEquals(testList.get(i).getDay(), studyManager.getModulesList().get(i).getDay());
            assertEquals(testList.get(i).getTimeSlot(), studyManager.getModulesList().get(i).getTimeSlot());
        }
    }

    /**
     * Tests delete module function of Study Manager class.
     * Checks that the deleted module is not in the module list.
     */
    @Test
    public void testDeleteModule() {
        studyManager.deleteModule(USER_INPUT_FOR_DELETE);

        // after deletion cs2113 should not be in the list
        for (Module m: studyManager.getModulesList()) {
            assertNotEquals(m.getModuleCode(), cs2113.getModuleCode());
            assertNotEquals(m.getCategory(), cs2113.getCategory());
            assertNotEquals(m.getDay(), cs2113.getDay());
            assertNotEquals(m.getTimeSlot(), cs2113.getTimeSlot());
        }
    }

    @Test
    public void testListModule() {
        outContent.reset();
        studyManager.listModules();
        String outputList = "Here are the modules in your schedule:\n"
                + "1: [Module] CS2113 Lecture: Friday, 4:00pm-6:00pm\n"
                + "2: [Module] CS3244 Tutorial: Monday, 2:00pm-3:00pm\n"
                + "3: [Module] EE4204 Exam: Monday, 10:00am-12:00pm\n";
        assertEquals(outputList,outContent.toString());
    }

    @Test
    public void testFindModule() {
        outContent.reset();
        studyManager.findModule("find CS3244");
        String outputFind = "Here are the matching modules in your list:\n"
                + "1: [Module] CS3244 Tutorial: Monday, 2:00pm-3:00pm\n";
        assertEquals(outputFind,outContent.toString());

        outContent.reset();
        studyManager.findModule("find cs");
        outputFind = "Here are the matching modules in your list:\n"
                + "1: [Module] CS2113 Lecture: Friday, 4:00pm-6:00pm\n"
                + "2: [Module] CS3244 Tutorial: Monday, 2:00pm-3:00pm\n";
        assertEquals(outputFind,outContent.toString());
    }

    @Test
    public void testEditModule() {
        Module editCheckerModule = new Module("CS2113", "Lecture",
                "Friday", "4:00pm-6:00pm");
        TextUi ui = new TextUi();
        String editCodeInput = "m/GEH1049";
        String editCategoryInput = "c/tut";
        String editDayInput = "d/Sunday";
        String editTimeInput = "t/11:00am-9:00pm";

        // Edit module code check
        Module moduleToEdit = studyManager.getModulesList().get(0);
        studyManager.editModuleCode(moduleToEdit, editCodeInput);
        editCheckerModule.setModuleCode("GEH1049");
        assertEquals(editCheckerModule.toString(), moduleToEdit.toString());

        // Edit module category check
        studyManager.editModuleCategory(moduleToEdit, editCategoryInput);
        editCheckerModule.setCategory("Tutorial");
        assertEquals(editCheckerModule.toString(), moduleToEdit.toString());

        // Edit module day check
        studyManager.editModuleDay(moduleToEdit, editDayInput);
        editCheckerModule.setDay("Sunday");
        assertEquals(editCheckerModule.toString(), moduleToEdit.toString());

        // Edit module time check
        studyManager.editModuleTime(moduleToEdit, editTimeInput);
        editCheckerModule.setTimeSlot("11:00am-9:00pm");
        assertEquals(editCheckerModule.toString(), moduleToEdit.toString());
    }

    @Test
    public void testEditCategoryError() {
        outContent.reset();
        String outputMessage = "Category has to be one of lec, tut, lab or exam\n";
        Module moduleToEdit = studyManager.getModulesList().get(0);
        studyManager.editModuleCategory(moduleToEdit, "Invalid category");
        assertEquals(outputMessage, outContent.toString());
    }

    @Test
    public void testEditCodeError() {
        outContent.reset();
        String outputMessage = "Your module code must be an alphanumeric parameter!\n";
        Module moduleToEdit = studyManager.getModulesList().get(0);
        studyManager.editModuleCode(moduleToEdit, "[]2e4234.`");
        assertEquals(outputMessage, outContent.toString());
    }

    @Test
    public void testEditDayError() {
        outContent.reset();
        String outputMessage = "You have entered an invalid day of the week\n"
                + "Accepted module day inputs are either a day of the week or a valid date of type DD-MM-YYYY\n";
        Module moduleToEdit = studyManager.getModulesList().get(0);
        studyManager.editModuleDay(moduleToEdit, "Anyday");
        assertEquals(outputMessage, outContent.toString());

        outContent.reset();
        outputMessage = "You have entered an invalid date\n"
                + "Accepted module day inputs are either a day of the week or a valid date of type DD-MM-YYYY\n";
        studyManager.editModuleDay(moduleToEdit, "1234556");
        assertEquals(outputMessage, outContent.toString());
    }

    @Test
    public void testEditTimeError() {
        outContent.reset();
        String outputMessage = "Accepted module time slot input is a valid timeslot of type HH:MMam/pm - HH:MMam/pm\n";
        Module moduleToEdit = studyManager.getModulesList().get(0);
        studyManager.editModuleTime(moduleToEdit, "2pm-4pm");
        assertEquals(outputMessage, outContent.toString());

    }

    @Test
    public void testReadIcs() {
        outContent.reset();
        ModuleCalendarReader moduleCalendarReader = new ModuleCalendarReader();
        moduleCalendarReader.readIcsFile("nusmods_calendar.ics");
        String calendarReaderOutput = "\n" +
                "I have found these modules from your ics file:\n\n"
                + "1: [Module] EG2401A Lecture: Wednesday, 6:00 pm-8:00 pm\n"
                + "2: [Module] EG2401A Tutorial: Friday, 9:00 am-10:00 am\n"
                + "3: [Module] CS2113 Lecture: Friday, 4:00 pm-6:00 pm\n"
                + "4: [Module] CS2113 Tutorial: Friday, 10:00 am-11:00 am\n"
                + "5: [Module] CS2113 Exam: 05-05-2022, 1:00 pm-3:00 pm\n"
                + "6: [Module] CG2271 Tutorial: Wednesday, 4:00 pm-5:00 pm\n"
                + "7: [Module] CG2271 Lecture: Wednesday, 10:00 am-12:00 pm\n"
                + "8: [Module] CG2271 Laboratory: Friday, 2:00 pm-4:00 pm\n"
                + "9: [Module] CG2271 Exam: 28-04-2022, 9:00 am-11:00 am\n"
                + "10: [Module] CS3244 Lecture: Thursday, 12:00 pm-2:00 pm\n"
                + "11: [Module] CS3244 Lecture: Monday, 2:00 pm-3:00 pm\n"
                + "12: [Module] CS3244 Tutorial: Monday, 5:00 pm-6:00 pm\n"
                + "13: [Module] CS3244 Exam: 23-04-2022, 9:00 am-11:00 am\n"
                + "14: [Module] EE4204 Lecture: Monday, 10:00 am-12:00 pm\n"
                + "15: [Module] EE4204 Tutorial: Wednesday, 1:00 pm-2:00 pm\n"
                + "16: [Module] EE4204 Exam: 29-04-2022, 9:00 am-11:00 am\n\n"
                + "I have added these to your existing schedule!\n";

        assertEquals(calendarReaderOutput,outContent.toString());
    }
}
