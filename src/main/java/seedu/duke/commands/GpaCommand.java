package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ModuleListEmptyException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.Grades;
import seedu.duke.util.StringConstants;

public class GpaCommand extends Command {

    private static final String GPA_MESSAGE = StringConstants.GPA_MESSAGE;

    private String result;

    public void calculateGpa(ModuleList moduleList) throws ModHappyException {
        if (Objects.isNull(moduleList.getModuleList())) {
            throw new ModuleListEmptyException();
        }
        int totalMc = 0;
        double weightedSum = 0.0;
        for (Module m : moduleList.getModuleList()) {
            Grades modularGrade = m.getModuleGrade();
            switch (modularGrade) {
            case CS:
            case CU:
            case S:
            case U:
            case NOT_ENTERED:
                // Intentional fallthrough
                break;
            default:
                int mc = m.getModularCredit();
                double modularGradePoint = m.getModuleGrade().getPoints();
                totalMc += mc;
                weightedSum += modularGradePoint * mc;
            }
        }
        double gpa = weightedSum / (double) totalMc;
        result = String.format(GPA_MESSAGE, gpa);
    }

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        calculateGpa(moduleList);
        return new CommandResult(result);
    }
}
