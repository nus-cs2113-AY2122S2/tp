package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Response;

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
