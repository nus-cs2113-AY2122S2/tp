package seedu.simplst;

import org.junit.jupiter.api.Test;
import seedu.simplst.parsers.AddParser;
import seedu.simplst.parsers.ViewParser;
import util.exceptions.*;

import static junit.framework.Assert.*;

public class ViewParserTest {
    Warehouse warehouse = new Warehouse(1000);

    @Test
    public void viewingGood_goodPresent_hasUnitGood() throws MissingFlagException,
            EmptyFieldException, WrongCommandException, InvalidFileException, InvalidObjectType {
        String userInputOneUG = "add ug/ sku/WC1 n/Wooden Chair d/German oak size/Medium";
        String userInputOneG = "add g/ sku/WC1 qty/10";
        AddParser addParserOneUG = new AddParser(warehouse, userInputOneUG);
        AddParser addParserOneG = new AddParser(warehouse, userInputOneG);
        addParserOneUG.initExtractParams();
        addParserOneUG.extractParams();
        addParserOneG.initExtractParams();
        addParserOneG.extractParams();

        String userInput = "view g/ sku/WC1";
        ViewParser viewParser = new ViewParser(warehouse, userInput);
        viewParser.initExtractParams();
        viewParser.extractParams();
        assertTrue(warehouse.hasUnitGood("WC1"));
    }

    @Test
    public void viewingGood_goodNotPresent_notHasUnitGood() throws MissingFlagException, EmptyFieldException,
            WrongCommandException {
        String userInput = "view g/ sku/WC2";
        ViewParser viewParser = new ViewParser(warehouse, userInput);
        viewParser.initExtractParams();
        viewParser.extractParams();
        assertFalse(warehouse.hasUnitGood("WC2"));
    }

    @Test
    public void viewOrderWrong_wrongInputFlag_catchException() throws MissingFlagException, EmptyFieldException,
            WrongCommandException {
        String userInput = "view o/";
        ViewParser viewParser = new ViewParser(warehouse, userInput);
        try {
            viewParser.initExtractParams();
        } catch (MissingFlagException e1) {
            assertFalse(true);
        }
        try {
            viewParser.extractParams();
        } catch (MissingFlagException e2) {
            assertTrue(true);
        }
    }

}
