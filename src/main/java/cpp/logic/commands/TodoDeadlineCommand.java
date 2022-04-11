package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.model.project.Todo;
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
        Project project;
        try {
            project = projectList.getProject(indexProj - 1);
            projectList.addTodoDeadline(indexProj, indexTodo, deadline);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.deadlineUnsuccessfully();
        }
        if (project == null) {
            return Response.deadlineUnsuccessfully();
        }
        Todo todo = project.getTodo(indexTodo);
        return Response.todoDeadlineSuccessfully(todo.getDescription(), todo.getDeadline());
    }
}
