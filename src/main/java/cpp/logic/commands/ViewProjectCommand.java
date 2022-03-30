package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.response.Response;

public class ViewProjectCommand extends Command {
    private final String projectTitle;

    public ViewProjectCommand(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    @Override
    public String execute(ProjectList projectList) {
        projectList.view(projectTitle);
        return Response.viewCommandExecuted();
    }
}
