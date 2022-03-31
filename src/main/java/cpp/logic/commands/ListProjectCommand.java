package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Response;

public class ListProjectCommand extends Command {
    public ListProjectCommand() {

    }


    @Override
    public String execute(ProjectList projectList) {
        projectList.printProject();
        return Response.listProjectsSuccessfully();
    }
}
