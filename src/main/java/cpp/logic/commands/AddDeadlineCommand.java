package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.response.Response;

public class AddDeadlineCommand extends Command {
    private final String projectName;
    private final String deadline;

    public AddDeadlineCommand(String projectName, String deadline) {
        this.projectName = projectName;
        this.deadline = deadline;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project = projectList.getProject(projectName);
        /*
        if (project == null) {
            return Response.addDeadlineUnsuccessfully();
        }
        try {
            project.setDeadline(deadline);
            return Response.addDeadlineSuccessfully(projectName, deadline);
        } catch (DateTimeParseException e) {
            return Response.dateFormatError();
        }
        */
        projectList.addDeadline(projectName, deadline);
        if (project == null) {
            return Response.addDeadlineUnsuccessfully();
        }
        return Response.addDeadlineSuccessfully(projectName, project.getDeadline());
    }
}
