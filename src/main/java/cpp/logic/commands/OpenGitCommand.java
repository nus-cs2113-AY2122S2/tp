package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Response;

public class OpenGitCommand extends Command {
    private final String projectTitle;

    public OpenGitCommand(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    @Override
    public String execute(ProjectList projectList) {
        projectList.openGit(projectTitle);
        return Response.openGitCommandExecuted();
    }
}
