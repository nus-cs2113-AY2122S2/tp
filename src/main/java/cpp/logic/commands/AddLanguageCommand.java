package cpp.logic.commands;

import cpp.Constants;
import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.response.Response;

public class AddLanguageCommand extends Command {
    private String projectName;
    private String language;

    public AddLanguageCommand(String projectName, String language) {
        this.projectName = projectName;
        this.language = language;
    }

    @Override
    public String execute(ProjectList projectList) {
        projectList.addLanguages(projectName, language);
        return Response.addLanguageSuccessfully();
    }
}
