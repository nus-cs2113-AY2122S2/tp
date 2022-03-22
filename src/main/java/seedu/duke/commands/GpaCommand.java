package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ModuleListEmptyException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.util.Grades;
import seedu.duke.util.StringConstants;

import java.util.Objects;

public class GpaCommand extends Command {

    private static final String GPA_MESSAGE = StringConstants.GPA_MESSAGE;

    private String result;
    private int mc;
    private double modularGradePoint;
    private Grades modularGrade;
    private float gpa = 0;
    private int totalMc = 0;
    private double sumOfProduct = 0.0; // product of modular grade point and mc

    public void calculateGpa(ModuleList moduleList) throws ModHappyException {
        if (Objects.isNull(moduleList.list)) {
            throw new ModuleListEmptyException();
        }
        for (Module m : moduleList.getModuleList()) {
            mc = m.getModularCredit();
            modularGradePoint = m.getModuleGrade().getPoints();
            modularGrade = m.getModuleGrade();
            switch (modularGrade) {
            case CS:
            case CU:
            case S:
            case U:
            case NOT_ENTERED:
                // Intentional fallthrough
                break;
            default:
                totalMc += mc;
                sumOfProduct += modularGradePoint * mc;
                gpa = (float) (sumOfProduct / totalMc);
            }
        }
        result = String.format(GPA_MESSAGE, gpa);
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        calculateGpa(moduleList);
        return new CommandResult(result);
    }
}
