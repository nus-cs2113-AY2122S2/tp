package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Response;

public class DeleteProjectCommand extends Command {
    private final String projectToDelete;

    public DeleteProjectCommand(String projectTitle) {
        this.projectToDelete = projectTitle;

    }

    @Override
    public String execute(ProjectList projectList) {
        try {
            projectList.deleteProject(projectToDelete);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such project named " + projectToDelete + ".");
            return Response.deleteProjectUnsuccessfully(projectToDelete);
        }

        return Response.deleteProjectSuccessfully(projectToDelete);
    }
}
