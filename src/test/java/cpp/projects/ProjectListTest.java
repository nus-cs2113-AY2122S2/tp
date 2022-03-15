package cpp.projects;

import cpp.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectListTest {
    private static final String PROJECT1NAME = "CS2113tP";
    private static final String PROJECT2NAME = "CS2113iP";
    private static final String PROJECT3NAME = "CS3005Project";
    private static final String TODO1 = "Set up the team repo.";
    private static final String TODO2 = "Complete user guide.";
    private ProjectList emptyProjectList;
    private ProjectList defaultProjectList;

    @BeforeEach
    void setUp() {
        emptyProjectList = new ProjectList();
        defaultProjectList = new ProjectList();
    }

    @Test
    void addProject() { //aims to test if addProject work well
        emptyProjectList.addProject(PROJECT1NAME);
        assertEquals(1, emptyProjectList.getProjectNo());
        emptyProjectList.addProject(PROJECT2NAME);
        assertEquals(2, emptyProjectList.getProjectNo());
    }

    @Test
    void addRepeatProject() { //aims to test if addProject can detect the problem of adding repeated project
        emptyProjectList.addProject(PROJECT1NAME);
        assertEquals(1, emptyProjectList.getProjectNo());
        emptyProjectList.addProject(PROJECT1NAME); //add same project to see if it is added repeatedly
        assertEquals(1, emptyProjectList.getProjectNo());
    }
    
    @Test
    void markTodoAsDone_indexOutOfRange() {
        defaultProjectList.addProject(PROJECT1NAME);
        defaultProjectList.addTodoToProject("1",TODO1);
        defaultProjectList.addTodoToProject("1",TODO2);
        String targetIndexString = "5";
        assertThrows(IndexOutOfBoundsException.class, () -> defaultProjectList.markTodoAsDone("1", targetIndexString));
    }
}