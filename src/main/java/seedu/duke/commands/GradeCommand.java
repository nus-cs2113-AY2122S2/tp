package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;
import seedu.duke.util.Grades;

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

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        Module targetModule = moduleList.getModule(moduleCode);
        addGradeToModule(targetModule);
        return new CommandResult(result);
    }

    /**
     * Sets grade of the specified module.
     */
    public void addGradeToModule(Module m) throws ModHappyException {
        if (Objects.isNull(m)) {
            throw new NoSuchModuleException();
        }
        boolean hasGrade = !Objects.equals(m.getModuleGrade(), Grades.NOT_ENTERED);
        if (hasGrade) {
            result = String.format(GRADE_CHANGED_MESSAGE, moduleCode);
        } else {
            result = String.format(GRADE_ADDED_MESSAGE, moduleCode);
        }
        m.setModuleGrade(moduleGrade);
    }
}