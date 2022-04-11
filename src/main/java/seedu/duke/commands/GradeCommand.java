package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.exceptions.InvalidGradeRemovalException;
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
     * @throws InvalidGradeRemovalException If the grade to be removed was not set in the first place
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration)
            throws NoSuchModuleException, InvalidGradeRemovalException {
        Module targetModule = moduleList.getModule(moduleCode);
        addGradeToModule(targetModule);
        return new CommandResult(result);
    }

    /**
     * Sets grade of the specified module.
     * @param module The module specified for the grade to be set
     * @throws InvalidGradeRemovalException If the grade to be removed was not set in the first place
     */
    public void addGradeToModule(Module module) throws InvalidGradeRemovalException {
        boolean hasGrade = !Objects.equals(module.getModuleGrade(), Grades.NOT_ENTERED);
        if (hasGrade) {
            result = String.format(GRADE_CHANGED_MESSAGE, moduleCode);
        } else if (Grades.getGradeEnum(moduleGrade) != Grades.NOT_ENTERED) {
            result = String.format(GRADE_ADDED_MESSAGE, moduleCode);
        } else {
            throw new InvalidGradeRemovalException();
        }
        module.setModuleGrade(moduleGrade);
    }
}