package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.util.StringConstants;

import java.util.Objects;

public class GradeCommand extends Command {

    private static final String GRADE_ADDED_MESSAGE = StringConstants.GRADE_ADDED_MESSAGE;
    private static final String GRADE_CHANGED_MESSAGE = StringConstants.GRADE_CHANGED_MESSAGE;
    private static final String NOT_ENTERED = StringConstants.NOT_ENTERED;

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
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        addGradeToModule(moduleList);
        return new CommandResult(result);
    }

    /**
     * Deletes given module from moduleList.
     *
     * @param moduleList List from which the module is to be deleted from.
     */
    public void addGradeToModule(ModuleList moduleList) throws ModHappyException {
        Module targetModule = moduleList.getModule(moduleCode);
        if (Objects.isNull(targetModule)) {
            throw new NoSuchModuleException();
        }
        boolean hasGrade = !Objects.equals(targetModule.getModuleGrade(), NOT_ENTERED);
        if (hasGrade) {
            result = String.format(GRADE_CHANGED_MESSAGE, moduleCode);
        } else {
            result = String.format(GRADE_ADDED_MESSAGE, moduleCode);
        }
        targetModule.setModuleGrade(moduleGrade);
    }

}
