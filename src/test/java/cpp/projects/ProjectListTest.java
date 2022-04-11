package cpp.projects;

import cpp.exceptions.NegativeIndexException;
import cpp.model.ProjectList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectListTest {
    private static final String PROJECT1NAME = "CS2113tP";
    private static final String PROJECT2NAME = "CS2113iP";
    private static final String PROJECT3NAME = "CS3005Project";
    private static final String TODO1 = "Set up the team repo.";
    private static final String TODO2 = "Complete user guide.";
    private static final String DEADLINE1 = "12/12/2024";
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
    void markTodoAsDone_negativeIndex() {
        int targetIndex = -1;
        assertThrows(IndexOutOfBoundsException.class, () -> defaultProjectList.markTodoAsDone(1, targetIndex));
    }

    @Test
    void deleteProjects() { //aims to test the deletion of projects
        emptyProjectList.addProject(PROJECT1NAME);
        assertThrows(IndexOutOfBoundsException.class, () -> defaultProjectList.deleteProject(PROJECT1NAME));

    }

    @Test
    void deleteProjects_ProjectsNotExist() { //aims to test the deletion of projects when the projects do not exist
        emptyProjectList.addProject(PROJECT1NAME);
        assertThrows(IndexOutOfBoundsException.class, () -> defaultProjectList.deleteProject(PROJECT3NAME));
        assertEquals(1, emptyProjectList.getProjectNo());
    }
}