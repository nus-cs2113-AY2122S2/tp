package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Response;

public class AddProjectDeadlineCommand extends Command {
    private final String projectName;
    private final String deadline;

    public AddProjectDeadlineCommand(String projectName, String deadline) {
        this.projectName = projectName;
        this.deadline = deadline;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project = projectList.getProject(projectName);
        /*
        if (project == null) {
            return Response.addProjectDeadlineUnsuccessfully();
        }
        try {
            project.setDeadline(deadline);
            return Response.addProjectDeadlineSuccessfully(projectName, deadline);
        } catch (DateTimeParseException e) {
            return Response.dateFormatError();
        }
        */
        projectList.addProjectDeadline(projectName, deadline);
        if (project == null) {
            return Response.addProjectDeadlineUnsuccessfully();
        }
        return Response.addProjectDeadlineSuccessfully(projectName, project.getDeadline());
    }
}
