package seedu.duke.util;

import static seedu.duke.util.NumberConstants.GRADE_POINT_A_AND_A_PLUS;
import static seedu.duke.util.NumberConstants.GRADE_POINT_A_MINUS;
import static seedu.duke.util.NumberConstants.GRADE_POINT_B_PLUS;
import static seedu.duke.util.NumberConstants.GRADE_POINT_B;
import static seedu.duke.util.NumberConstants.GRADE_POINT_B_MINUS;
import static seedu.duke.util.NumberConstants.GRADE_POINT_C_PLUS;
import static seedu.duke.util.NumberConstants.GRADE_POINT_C;
import static seedu.duke.util.NumberConstants.GRADE_POINT_D_PLUS;
import static seedu.duke.util.NumberConstants.GRADE_POINT_D;
import static seedu.duke.util.NumberConstants.GRADE_POINT_ZERO;
import static seedu.duke.util.StringConstants.PLUS_STR;
import static seedu.duke.util.StringConstants.MINUS_STR;
import static seedu.duke.util.StringConstants.NOT_ENTERED_STR;
import static seedu.duke.util.StringConstants.PLUS;
import static seedu.duke.util.StringConstants.DASH;

public enum Grades {
    A_PLUS(GRADE_POINT_A_AND_A_PLUS),
    A(GRADE_POINT_A_AND_A_PLUS),
    A_MINUS(GRADE_POINT_A_MINUS),
    B_PLUS(GRADE_POINT_B_PLUS),
    B(GRADE_POINT_B),
    B_MINUS(GRADE_POINT_B_MINUS),
    C_PLUS(GRADE_POINT_C_PLUS),
    C(GRADE_POINT_C),
    D_PLUS(GRADE_POINT_D_PLUS),
    D(GRADE_POINT_D),
    F(GRADE_POINT_ZERO),
    S(GRADE_POINT_ZERO),
    U(GRADE_POINT_ZERO),
    CS(GRADE_POINT_ZERO),
    CU(GRADE_POINT_ZERO),
    NOT_ENTERED(GRADE_POINT_ZERO);

    private final double points;
    Grades(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    @Override
    public String toString() {
        final String name = name();
        if (name.contains(PLUS_STR)) {
            return name.charAt(0) + PLUS;
        } else if (name.contains(MINUS_STR)) {
            return name.charAt(0) + DASH;
        } else if (name.equals(NOT_ENTERED_STR)) {
            return DASH;
        } else {
            return name;
        }
    }

    public static Grades getGradeEnum(String moduleGrade) {
        for (Grades grade : Grades.values()) {
            String gradeStr = grade.toString();
            if (moduleGrade.equals(gradeStr)) {
                return grade;
            }
        }
        return NOT_ENTERED;
    }
}
