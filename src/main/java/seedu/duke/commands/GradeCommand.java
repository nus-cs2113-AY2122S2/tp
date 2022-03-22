package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import seedu.duke.util.Grades;

public class GradeCommand extends Command {

    private static final String GRADE_ADDED_MESSAGE = StringConstants.GRADE_ADDED_MESSAGE;
    private static final String GRADE_CHANGED_MESSAGE = StringConstants.GRADE_CHANGED_MESSAGE;
    private static final String NOT_ENTERED = StringConstants.NOT_ENTERED_STR;

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

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        addGradeToModule(moduleList);
        return new CommandResult(result);
    }

    /**
     * Adds/Changes grade to specified module from moduleList.
     *
     * @param moduleList List from which the module which grade is to be added/changed.
     */
    public void addGradeToModule(ModuleList moduleList) throws ModHappyException {
        Module targetModule = moduleList.getModule(moduleCode);
        if (Objects.isNull(targetModule)) {
            throw new NoSuchModuleException();
        }
        boolean hasGrade = !Objects.equals(targetModule.getModuleGrade(), Grades.NOT_ENTERED);
        if (hasGrade) {
            result = String.format(GRADE_CHANGED_MESSAGE, moduleCode);
        } else {
            result = String.format(GRADE_ADDED_MESSAGE, moduleCode);
        }
        targetModule.setModuleGrade(moduleGrade);
    }

}