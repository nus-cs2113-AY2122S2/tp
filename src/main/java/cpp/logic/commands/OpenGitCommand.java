package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Constants;
import cpp.ui.Response;

public class OpenGitCommand extends Command {
    private final int projectIndex;

    public OpenGitCommand(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project;
        try {
            project = projectList.getProject(projectIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.openGitUnsuccessfully(projectIndex);
        }
        if (project == null) {
            return Response.openGitUnsuccessfully(projectIndex);
        }
        projectList.openGit(project.getTitle());
        return Response.openGitSuccessfully(project.getTitle());
    }
}
