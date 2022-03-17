package seedu.allonus.modules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests the working of StudyManager class.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyManagerTest {

    public static final String USER_INPUT_FOR_DELETE = "rm 1";
    private Module cs2113;
    private Module cs3244;
    private Module ee4204;

    ArrayList<Module> testList = new ArrayList<>();

    /**
     * Creates three new module objects and adds them to the test module list.
     */
    @BeforeAll
    public void setUp() {
        cs2113 = new Module("CS2113", "Lecture", "Friday", "4pm-6pm");
        cs3244 = new Module("CS3244", "Tutorial", "Monday", "2pm-3pm");
        ee4204 = new Module("EE4204", "Exam", "Monday", "10am-12pm");

        testList.add(cs2113);
        testList.add(cs3244);
        testList.add(ee4204);
    }

    /**
     * Tests add module function of Study Manager class.
     * Ensures that the contents of the module list is equal to the test list.
     */
    @Test
    public void testAddModule() {
        StudyManager studyManager = new StudyManager();

        String userInputCs2113 = "add m/CS2113 c/lec d/Friday t/4pm-6pm";
        String userInputCs3244 = "add m/CS3244 c/tut d/Monday t/2pm-3pm";
        String userInputEe4204 = "add m/EE4204 c/exam d/Monday t/10am-12pm";

        studyManager.addModule(userInputCs2113);
        studyManager.addModule(userInputCs3244);
        studyManager.addModule(userInputEe4204);

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
        StudyManager studyManager = new StudyManager();
        studyManager.deleteModule(USER_INPUT_FOR_DELETE);

        // after deletion cs2113 should not be in the list
        for (Module m: studyManager.getModulesList()) {
            assertNotEquals(m.getModuleCode(), cs2113.getModuleCode());
            assertNotEquals(m.getCategory(), cs2113.getCategory());
            assertNotEquals(m.getDay(), cs2113.getDay());
            assertNotEquals(m.getTimeSlot(), cs2113.getTimeSlot());
        }
    }
}
