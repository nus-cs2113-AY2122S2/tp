package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Constants;
import cpp.ui.Response;

public class ChangeGitHubLinkCommand extends Command {
    private final int projectIndex;
    private final String githubLink;

    public ChangeGitHubLinkCommand(int projectIndex, String githubLink) {
        this.projectIndex = projectIndex;
        this.githubLink = githubLink;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project;
        try {
            project = projectList.getProject(projectIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.deadlineUnsuccessfully();
        }
        projectList.addGithubLink(projectIndex, githubLink);
        if (project == null) {
            return Response.addGithubLinkUnsuccessfully();
        }
        return Response.addGithubLinkSuccessfully(project.getTitle(), project.getGitHubLink());
    }
}
