package seedu.simplst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitGoodTest {

    @Test
    public void convertCapacity_integerInput_capacityDefault() {
        String capacity = "1";
        UnitGood testUnitGood = new UnitGood("WC1", "Wooden Chair", "German Oak", capacity);
        assertEquals(Capacity.MEDIUM, testUnitGood.getCapacity());
    }

    @Test
    public void convertCapacity_otherSymbols_capacityDefault() {
        String capacity = "./>5";
        UnitGood testUnitGood = new UnitGood("WC1", "Wooden Chair", "German Oak", capacity);
        assertEquals(Capacity.MEDIUM, testUnitGood.getCapacity());
    }

    @Test
    public void convertCapacity_caseSensitivity_capacitySmall() {
        String smallCaseCapacity = "small";
        String upperCaseCapacity = "SMALL";
        String mixCaseCapacity = "sMaLL";
        UnitGood smallCaseUnitGood = new UnitGood("WC1", "Wooden Chair", "German Oak",
                smallCaseCapacity);
        UnitGood upperCaseUnitGood = new UnitGood("WC1", "Wooden Chair", "German Oak",
                upperCaseCapacity);
        UnitGood mixCaseUnitGood = new UnitGood("WC1", "Wooden Chair", "German Oak",
                mixCaseCapacity);
        assertEquals(Capacity.SMALL, smallCaseUnitGood.getCapacity());
        assertEquals(Capacity.SMALL, upperCaseUnitGood.getCapacity());
        assertEquals(Capacity.SMALL, mixCaseUnitGood.getCapacity());
    }
}
