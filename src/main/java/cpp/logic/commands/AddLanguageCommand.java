package cpp.logic.commands;

import cpp.model.ProjectList;
import cpp.ui.Constants;
import cpp.ui.Response;

public class AddLanguageCommand extends Command {
    private int projectIndex;
    private String language;

    public AddLanguageCommand(int projectIndex, String language) {
        this.projectIndex = projectIndex;
        this.language = language;
    }

    @Override
    public String execute(ProjectList projectList) {
        try {
            projectList.addLanguages(projectIndex, language);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Constants.INDEX_OUT_OF_RANGE);
        }
        return Response.addLanguageSuccessfully();
    }
}
