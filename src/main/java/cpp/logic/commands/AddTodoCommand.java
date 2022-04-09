package cpp.logic.commands;

import cpp.ui.Constants;
import cpp.model.ProjectList;
import cpp.ui.Response;

public class AddTodoCommand extends Command {
    private final int projectIndex;
    private final String todo;

    public AddTodoCommand(int projectIndex, String todo) {
        this.projectIndex = projectIndex;
        this.todo = todo;
    }

    @Override
    public String execute(ProjectList projectList) {
        try {
            projectList.addTodoToProject(projectIndex, todo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Execution result:" + Constants.INDEX_OUT_OF_RANGE);
            return Response.addTodoUnsuccessfully(todo);
        }
        return Response.addTodoSuccessfully(todo);
    }


}
