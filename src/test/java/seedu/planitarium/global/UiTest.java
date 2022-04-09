package seedu.planitarium.global;

import org.junit.jupiter.api.Test;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final Double VALID_AMOUNT = 1000.0;
    private static final Double NEGATIVE_AMOUNT = -1000.0;
    private static final Double THREE_DP_AMOUNT = 123.456;
    private static final String FORMATTED_VALID = "$1000.00";
    private static final String FORMATTED_NEGATIVE = "-$1000.00";
    private static final String FORMATTED_TWO_DP = "$123.46";

    @Test
    public void formatValue_negativeValue_minusInFrontOfDollar() {
        Family family = new Family();
        Double value = NEGATIVE_AMOUNT;
        assertEquals(FORMATTED_NEGATIVE, UI.formatValue(value));
    }

    @Test
    public void formatValue_notNegativeValue_noMinusInFrontOfDollar() {
        Family family = new Family();
        Double value = VALID_AMOUNT;
        assertEquals(FORMATTED_VALID, UI.formatValue(value));
    }

    @Test void formatValue_moreThan2DecimalPointValue_print2DP() {
        Family family = new Family();
        Double value = THREE_DP_AMOUNT;
        assertEquals(FORMATTED_TWO_DP, UI.formatValue(value));
    }
}
