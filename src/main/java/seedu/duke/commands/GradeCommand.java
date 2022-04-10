package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import seedu.duke.util.Grades;

//@@author heekit73098
public class GradeCommand extends Command {
    private static final String GRADE_ADDED_MESSAGE = StringConstants.GRADE_ADDED_MESSAGE;
    private static final String GRADE_CHANGED_MESSAGE = StringConstants.GRADE_CHANGED_MESSAGE;

    private final String moduleCode;
    private final String moduleGrade;
    private String result;

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleGrade() {
        return moduleGrade;
    }

    public GradeCommand(String moduleCode, String moduleGrade) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
    }

    /**
     * Sets a grade to the specified module.
     * @param moduleList The list of modules
     * @param configuration The configuration settings of the application
     * @return A new {@code CommandResult} with the result string
     * @throws NoSuchModuleException If the module does not exist
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws NoSuchModuleException {
        Module targetModule = moduleList.getModule(moduleCode);
        addGradeToModule(targetModule);
        return new CommandResult(result);
    }

    /**
     * Sets grade of the specified module.
     * @param module The module specified for the grade to be set
     */
    public void addGradeToModule(Module module) {
        boolean hasGrade = !Objects.equals(module.getModuleGrade(), Grades.NOT_ENTERED);
        if (hasGrade) {
            result = String.format(GRADE_CHANGED_MESSAGE, moduleCode);
        } else {
            result = String.format(GRADE_ADDED_MESSAGE, moduleCode);
        }
        module.setModuleGrade(moduleGrade);
    }
}