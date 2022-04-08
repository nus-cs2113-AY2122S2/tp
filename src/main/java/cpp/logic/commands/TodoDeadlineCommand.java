package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Constants;
import cpp.ui.Response;

public class TodoDeadlineCommand extends Command {
    private final int indexProj;
    private final int indexTodo;
    private final String deadline;

    public TodoDeadlineCommand(int indexProj, int indexTodo, String deadline) {
        this.indexProj = indexProj;
        this.indexTodo = indexTodo;
        this.deadline = deadline;
    }

    @Override
    public String execute(ProjectList projectList) {
        try {
            projectList.addTodoDeadline(indexProj, indexTodo, deadline);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.markTodoUnsuccessfully();
        }
        return Response.markTodoSuccessfully();
    }
}
