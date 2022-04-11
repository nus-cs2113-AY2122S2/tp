package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Response;


public class AddProjectCommand extends Command {
    private final String projectName;

    public AddProjectCommand(String projectName) {
        this.projectName = projectName;
    }

    public String execute(ProjectList projectList) {
        projectList.addProject(projectName);
        return Response.addProjectSuccessfully(projectName);
    }




}
