package cpp.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProjectTest {
    @Test
    public void testDeadline() {
        Project testProj = new Project("Testing");
        testProj.setDeadline("Sunday");
        assertEquals(testProj.getDeadline(), "Sunday");
    }

}
