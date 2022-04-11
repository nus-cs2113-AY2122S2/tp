package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Constants;
import cpp.ui.Response;

public class ViewProjectCommand extends Command {
    private final int projectIndex;

    public ViewProjectCommand(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public String execute(ProjectList projectList) {
        Project project;
        try {
            project = projectList.getProject(projectIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.viewCommandUnsuccessfully();
        }
        if (project == null) {
            return Response.viewCommandUnsuccessfully();
        }
        projectList.view(project.getTitle());
        return Response.viewCommandExecuted();
    }
}
