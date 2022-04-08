package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Constants;
import cpp.ui.Response;

public class ProjectDeadlineCommand extends Command {
    private final int projectIndex;
    private final String deadline;

    public ProjectDeadlineCommand(int projectIndex, String deadline) {
        this.projectIndex = projectIndex;
        this.deadline = deadline;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project;
        try {
            project = projectList.getProject(projectIndex - 1);
            projectList.addProjectDeadline(projectIndex, deadline);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.addProjectDeadlineUnsuccessfully();
        }
        if (project == null) {
            return Response.addProjectDeadlineUnsuccessfully();
        }
        return Response.addProjectDeadlineSuccessfully(project.getTitle(), project.getDeadline());
    }
}
