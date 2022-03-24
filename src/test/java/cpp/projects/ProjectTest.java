package cpp.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {
    private static final String TESTPROJNAME = "CS2113tP";
    private static final String TODO1 = "Set up the team repo.";
    private static final String TODO2 = "Complete user guide.";
    private static final String DEADLINE1 = "12/12/2024";
    private Project testProj;

    @BeforeEach
    void setUp() {
        testProj = new Project(TESTPROJNAME);
    }

    @Test
    public void testTitle() {
        assertEquals(testProj.getTitle(), TESTPROJNAME);
    }

    @Test
    public void testDeadline() {
        testProj.setDeadline("Sunday");
        assertEquals(testProj.getDeadline(), "Sunday");
    }

    @Test
    public void testTodo() {
        testProj.addTodo(TODO1);
        testProj.addTodo(TODO2);
        //System.out.println(testProj.getTodo(1));
        assertEquals(testProj.getTodo(1).toString(), "[ ]" + TODO1);
        testProj.markTodoAsDone(2);
        assertEquals(testProj.getTodo(2).toString(), "[X]" + TODO2);
    }

}
