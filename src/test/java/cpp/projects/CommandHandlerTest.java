package cpp.projects;

import cpp.exceptions.IllegalCommandException;
import cpp.model.ProjectList;
import cpp.logic.CommandHandler;
import cpp.ui.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandHandlerTest {
    private static final String PROJECT1NAME = "CS2113tP";
    private static final String PROJECT2NAME = "CS2113iP";
    private static final String PROJECT3NAME = "CS3005Project";
    private static final String TODO1 = "Set up the team repo.";
    private static final String TODO2 = "Complete user guide.";
    private CommandHandler defaultCommandHandler;
    private ProjectList defaultProjectList;
    private Response response;


    @BeforeEach
    void setUp() {
        defaultCommandHandler = new CommandHandler();
        defaultProjectList = new ProjectList();
        defaultProjectList.addProject(PROJECT1NAME);
        defaultProjectList.addTodoToProject(1, TODO1);
        defaultProjectList.addTodoToProject(1, TODO2);
    }

    @Test
    void view_noProjectSpecified() {
        assertThrows(IllegalCommandException.class, () ->
            defaultCommandHandler.handleUserInput(defaultProjectList, "view")
        );
    }

    @Test
    void input_unknownCommand() {
        assertThrows(IllegalCommandException.class, () ->
                defaultCommandHandler.handleUserInput(defaultProjectList, "unknownCommand")
        );
    }

    @Test
    public void testAddProject() throws IllegalCommandException {
        String status = defaultCommandHandler.handleUserInput(defaultProjectList, "addproject temp");
        assertEquals(status, "The project temp has been added successfully.");
    }

    @Test
    public void testDeleteProject() throws IllegalCommandException {
        String status = defaultCommandHandler.handleUserInput(defaultProjectList, "deleteproject CS2113tP");
        assertEquals(status, "CS2113tP deleted successfully.");
    }

    @Test
    public void testToDo() throws IllegalCommandException {
        String status = defaultCommandHandler.handleUserInput(defaultProjectList, "todo 1 temp");
        assertEquals(status, "Todo temp has been added successfully.");
    }

    @Test
    public void testListProjects() throws IllegalCommandException {
        String status = defaultCommandHandler.handleUserInput(defaultProjectList, "listprojects");
        assertNotNull(status);
    }

    @Test
    public void testMark() throws IllegalCommandException {
        String status = defaultCommandHandler.handleUserInput(defaultProjectList, "mark 1 1");
        assertEquals(status, response.markTodoSuccessfully());
    }

    @Test
    public void testDeadline() throws IllegalCommandException {
        String status = defaultCommandHandler.handleUserInput(
                    defaultProjectList, "projdeadline 1 2024-12-12");
        assertEquals(status, response.addProjectDeadlineSuccessfully(PROJECT1NAME, "2024-12-12"));
    }

    @Test
    public void testInvalidCommand() throws IllegalCommandException {
        assertThrows(IllegalCommandException.class, () ->
                defaultCommandHandler.handleUserInput(defaultProjectList,"zzzzz")
        );
    }


}
