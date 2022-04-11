package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.ui.Constants;
import cpp.ui.Response;

public class ListLanguageCommand extends Command {
    private final int projectIndex;

    public ListLanguageCommand(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    public String execute(ProjectList projectList) {
        Project project;
        try {
            project = projectList.getProject(projectIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
            return Response.listLanguageUnsuccessfully();
        }
        if (project == null) {
            return Response.listLanguageUnsuccessfully();
        }
        projectList.listLanguages(project.getTitle());
        return Response.listLanguageSuccessfully();
    }
}
