package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Response;

public class AddTodoDeadlineCommand extends Command {
    private final int indexProj;
    private final int indexTodo;
    private final String deadline;

    public AddTodoDeadlineCommand(int indexProj, int indexTodo, String deadline) {
        this.indexProj = indexProj;
        this.indexTodo = indexTodo;
        this.deadline = deadline;
    }

    @Override
    public String execute(ProjectList projectList) {
        try {
            projectList.addTodoDeadline(indexProj, indexTodo, deadline);
        } catch (IndexOutOfBoundsException e) {
            return Response.markTodoUnsuccessfully();
        }
        return Response.markTodoSuccessfully();
    }
}
