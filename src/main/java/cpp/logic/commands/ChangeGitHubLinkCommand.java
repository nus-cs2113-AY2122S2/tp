package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Response;

public class ChangeGitHubLinkCommand extends Command {
    private final String projectName;
    private final String githubLink;

    public ChangeGitHubLinkCommand(String projectName, String githubLink) {
        this.projectName = projectName;
        this.githubLink = githubLink;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project = projectList.getProject(projectName);
        projectList.addGithubLink(projectName, githubLink);
        if (project == null) {
            return Response.addGithubLinkUnsuccessfully();
        }
        return Response.addGithubLinkSuccessfully(projectName, project.getDeadline());
    }
}
