package cpp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cpp.model.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {
    private static final String TESTPROJNAME = "CS2113tP";
    private static final String TODO1 = "Set up the team repo.";
    private static final String TODO2 = "Complete user guide.";
    private static final String DEADLINE1 = "2222-02-22";
    private static final String NO_DEADLINE = "No deadline specified";
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
        testProj.setDeadline(DEADLINE1);
        assertEquals(testProj.getDeadline(), DEADLINE1);
    }

    @Test
    public void testTodo() {
        testProj.addTodo(TODO1);
        testProj.addTodo(TODO2);
        //System.out.println(testProj.getTodo(1));
        assertEquals(testProj.getTodo(1).toString(), "[ ] " + TODO1 + ": " + NO_DEADLINE);
        testProj.markTodoAsDone(2);
        assertEquals(testProj.getTodo(2).toString(), "[X] " + TODO2 + ": " + NO_DEADLINE);
    }

}
