package seedu.duke.commands;

import seedu.duke.exceptions.GpaNotComputableException;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.Grades;
import seedu.duke.util.NumberConstants;
import seedu.duke.util.StringConstants;

//@@author Yzkkk
public class GpaCommand extends Command {

    private static final String GPA_MESSAGE = StringConstants.GPA_MESSAGE;
    private static final int MAXIMUM_TOTAL_CREDITS = NumberConstants.MAXIMUM_TOTAL_CREDITS;

    private String result;

    /**
     * Calculates GPA based on currently stored module grades.
     * @param moduleList List from which the grades are retrieved
     * @throws GpaNotComputableException If the gpa is not computable
     */
    public void calculateGpa(ModuleList moduleList) throws GpaNotComputableException {
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
            if (totalMc > MAXIMUM_TOTAL_CREDITS) {
                // Prevent integer overflow
                throw new GpaNotComputableException();
            }
        }
        if (totalMc == 0) {
            throw new GpaNotComputableException();
        }
        double gpa = weightedSum / (double) totalMc;
        result = String.format(GPA_MESSAGE, gpa);
    }

    /**
     * Calculates the gpa.
     * @param moduleList The list of modules
     * @param configuration The configuration settings of the application
     * @return A new {@code CommandResult} with the result string
     * @throws GpaNotComputableException If the gpa is not computable
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws GpaNotComputableException {
        calculateGpa(moduleList);
        return new CommandResult(result);
    }
}
