package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.response.Response;

public class MarkCommand extends Command {
    private final int projectIndex;
    private final int todoIndex;

    public MarkCommand(int projectIndex, int todoIndex) {
        this.projectIndex = projectIndex;
        this.todoIndex = todoIndex;
    }

    @Override
    public String execute(ProjectList projectList) {
        try {
            projectList.markTodoAsDone(projectIndex, todoIndex);
        } catch (IndexOutOfBoundsException e) {
            return Response.markTodoUnsuccessfully();
        }
        return Response.markTodoSuccessfully();
    }
}
