package seedu.simplst;

import org.junit.jupiter.api.Test;
import seedu.simplst.parsers.AddParser;
import seedu.simplst.parsers.RemoveParser;
import util.exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RemoveParserTest {
    Warehouse warehouse = new Warehouse(1000);
    //assertNull after removing for goods and ug respectively
    @Test
    public void removeUnitGood_addAndRemoveUG_notNull() throws MissingFlagException, EmptyFieldException,
            WrongCommandException, InvalidFileException, InvalidObjectType {
        String userInputOne = "add ug/ sku/WC1 n/Wooden Chair d/German oak size/Medium";
        AddParser addParser = new AddParser(warehouse, userInputOne);
        addParser.initExtractParams();
        addParser.extractParams();
        String userInput = "remove ug/ sku/WC1";
        RemoveParser removeParser = new RemoveParser(warehouse, userInput);
        removeParser.initExtractParams();
        UnitGood ug = warehouse.getUnitGoodHashMap().get("WC1");
        Good g = warehouse.getGoodList().get("WC1");
        assertNotNull(ug);

        removeParser.extractParams();
        assertNotNull(ug);
        assertEquals(0, g.getQuantity());
    }
}
