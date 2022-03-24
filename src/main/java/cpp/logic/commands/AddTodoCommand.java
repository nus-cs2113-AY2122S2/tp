package cpp.logic.commands;

import cpp.Constants;
import cpp.model.ProjectList;
import cpp.response.Response;

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
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.addTodoUnsuccessfully(todo);
        }

        return Response.addTodoSuccessfully(todo);
    }


}
