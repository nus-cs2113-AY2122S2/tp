package cpp.projects;

import cpp.exceptions.NegativeIndexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        defaultProjectList = new ProjectList();
        defaultProjectList.addProject(PROJECT1NAME);
        defaultProjectList.addTodoToProject("1",TODO1);
        defaultProjectList.addTodoToProject("1",TODO2);

    }

    @Test
    void markTodoAsDone_negativeIndex() {
        String targetIndexString = "-1";
        assertThrows(NegativeIndexException.class, () -> defaultProjectList.markTodoAsDone("1", targetIndexString));
    }
}