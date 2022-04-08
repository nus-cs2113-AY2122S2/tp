package cpp.logic.commands;

import cpp.exceptions.IllegalCommandException;
import cpp.model.ProjectList;
import cpp.ui.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandsTest {
    private static final String PROJECT1NAME = "CS2113tP";
    private static final String PROJECT2NAME = "CS2113iP";
    private static final String PROJECT3NAME = "CS3005Project";
    private static final String TODO1 = "Set up the team repo.";
    private static final String TODO2 = "Complete user guide.";
    private static final String TODO3 = "Something else.";
    public static final String DEADLINE = "2023-12-12";
    private ProjectList defaultProjectList;
    private Response response;


    @BeforeEach
    void setUp() {
        defaultProjectList = new ProjectList();
        defaultProjectList.addProject(PROJECT1NAME);
        defaultProjectList.addTodoToProject("1",TODO1);
        defaultProjectList.addTodoToProject("1",TODO2);
    }

    @Test
    public void testAddDeadline() throws IllegalCommandException {
        ProjectDeadlineCommand projectDeadlineCommand = new ProjectDeadlineCommand(1, DEADLINE);
        String status = projectDeadlineCommand.execute(defaultProjectList);
        assertEquals(status, "Deadline added to " + PROJECT1NAME + ": " + DEADLINE);
    }

    @Test
    public void testAddProject() throws IllegalCommandException {
        AddProjectCommand addProjectCommand = new AddProjectCommand(PROJECT1NAME);
        String status = addProjectCommand.execute(defaultProjectList);
        assertEquals(status, response.addProjectSuccessfully(PROJECT1NAME));
    }

    @Test
    public void testAddTodoCommand() throws IllegalCommandException {
        AddTodoCommand addTodoCommand = new AddTodoCommand(1, TODO3);
        String status = addTodoCommand.execute(defaultProjectList);
        assertEquals(status, response.addTodoSuccessfully(TODO3));
    }

    @Test
    public void testDeleteProject() throws IllegalCommandException {
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(PROJECT1NAME);
        String status = deleteProjectCommand.execute(defaultProjectList);
        assertEquals(status, response.deleteProjectSuccessfully(PROJECT1NAME));
    }

    @Test
    public void testListProject() throws IllegalCommandException {
        ListProjectCommand listProjectCommand = new ListProjectCommand();
        String status = listProjectCommand.execute(defaultProjectList);
        assertEquals(status, response.listProjectsSuccessfully());
    }

    @Test
    public void testMarkProject() throws IllegalCommandException {
        MarkCommand markCommand = new MarkCommand(1, 1);
        String status = markCommand.execute(defaultProjectList);
        assertEquals(status, response.markTodoSuccessfully());
    }

    @Test
    public void testViewProject() throws IllegalCommandException {
        ViewProjectCommand viewProjectCommand = new ViewProjectCommand(PROJECT1NAME);
        String status = viewProjectCommand.execute(defaultProjectList);
        assertEquals(status, response.viewCommandExecuted());
    }
}
