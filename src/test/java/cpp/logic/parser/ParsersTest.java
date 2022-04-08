package cpp.logic.parser;

import cpp.exceptions.IllegalCommandException;
import cpp.model.ProjectList;
import cpp.ui.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParsersTest {
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
    public void testAddDeadlineCommandParser() throws IllegalCommandException {
        String[] input = new String[1];
        input[0] = "dummy";
        ProjectDeadlineCommandParser addProjectDeadlineCommandParser = new ProjectDeadlineCommandParser();
        assertThrows(IllegalCommandException.class, () ->
                addProjectDeadlineCommandParser.parse(input)
        );
        String[] input2 = new String[3];
        input2[0] = "adddeadline";
        input2[1] = "1";
        input2[2] = "2023-12-12";
        assertNotNull(addProjectDeadlineCommandParser.parse(input2));
    }

    @Test
    public void testAddProjectParser() throws IllegalCommandException {
        String[] input = new String[1];
        input[0] = "dummy";
        AddProjectCommandParser addProjectCommandParser = new AddProjectCommandParser();
        assertThrows(IllegalCommandException.class, () ->
                addProjectCommandParser.parse(input)
        );
        String[] input2 = new String[3];
        input2[0] = "adddproject";
        input2[1] = "temporary";
        input2[2] = "project ";
        assertNotNull(addProjectCommandParser.parse(input2));
    }

    @Test
    public void testAddTodoProjectParser() throws IllegalCommandException {
        String[] input = new String[1];
        input[0] = "dummy";
        AddTodoCommandParser addTodoCommandParser = new AddTodoCommandParser();
        assertThrows(IllegalCommandException.class, () ->
                addTodoCommandParser.parse(input)
        );
        String[] input2 = new String[3];
        input2[0] = "todo";
        input2[1] = "1";
        input2[2] = "get 'er done";
        assertNotNull(addTodoCommandParser.parse(input2));
    }

    @Test
    public void testDeleteProjectParser() throws IllegalCommandException {
        String[] input = new String[1];
        input[0] = "dummy";
        DeleteProjectCommandParser deleteProjectCommandParser = new DeleteProjectCommandParser();
        assertThrows(IllegalCommandException.class, () ->
                deleteProjectCommandParser.parse(input)
        );
        String[] input2 = new String[3];
        input2[0] = "deleteproject";
        input2[1] = PROJECT1NAME;
        assertNotNull(deleteProjectCommandParser.parse(input2));
    }

    @Test
    public void testMarkCommandParser() throws IllegalCommandException {
        String[] input = new String[1];
        input[0] = "dummy";
        MarkCommandParser markCommandParser = new MarkCommandParser();
        assertThrows(IllegalCommandException.class, () ->
                markCommandParser.parse(input)
        );
        String[] input2 = new String[3];
        input2[0] = "mark";
        input2[1] = "-1";
        input2[2] = "-5";
        assertThrows(IllegalCommandException.class, () ->
                markCommandParser.parse(input2)
        );
        input2[1] = "1";
        input2[2] = "1";
        assertNotNull(markCommandParser.parse(input2));
    }

    @Test
    public void testViewProjectParser() throws IllegalCommandException {
        String[] input = new String[1];
        input[0] = "dummy";
        ViewProjectCommandParser viewProjectCommandParser = new ViewProjectCommandParser();
        assertThrows(IllegalCommandException.class, () ->
                viewProjectCommandParser.parse(input)
        );
        String[] input2 = new String[3];
        input2[0] = "view";
        input2[1] = PROJECT1NAME;
        assertNotNull(viewProjectCommandParser.parse(input2));
    }

}
