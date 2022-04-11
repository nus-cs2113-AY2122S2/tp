package seedu.simplst;

import org.junit.jupiter.api.Test;
import seedu.simplst.parsers.AddParser;
import seedu.simplst.parsers.TotalParser;
import util.exceptions.MissingFlagException;
import util.exceptions.EmptyFieldException;
import util.exceptions.WrongCommandException;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.NullException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertFalse;

public class TotalParserTest {
    Warehouse warehouse = new Warehouse(1000);

    @Test
    public void totalGoods_moreThanOneGoodsInput_correctNumber() throws MissingFlagException, EmptyFieldException,
            WrongCommandException, InvalidFileException, InvalidObjectType, NullException {
        String userInputOneUG = "add ug/ sku/WC1 n/Wooden Chair d/German oak size/Medium";
        String userInputOneG = "add g/ sku/WC1 qty/10";
        AddParser addParserOneUG = new AddParser(warehouse, userInputOneUG);
        AddParser addParserOneG = new AddParser(warehouse, userInputOneG);
        addParserOneUG.initExtractParams();
        addParserOneUG.extractParams();
        addParserOneG.initExtractParams();
        addParserOneG.extractParams();
        String userInputTwoUG = "add ug/ sku/WC2 n/Metal Table d/France Iron size/Medium";
        String userInputTwoG = "add g/ sku/WC2 qty/20";
        AddParser addParserTwoUG = new AddParser(warehouse, userInputTwoUG);
        AddParser addParserTwoG = new AddParser(warehouse, userInputTwoG);
        addParserTwoUG.initExtractParams();
        addParserTwoUG.extractParams();
        addParserTwoG.initExtractParams();
        addParserTwoG.extractParams();

        String userInput = "total g/";
        TotalParser totalParser = new TotalParser(warehouse, userInput);
        try {
            totalParser.initExtractParams();
            totalParser.extractParams();
        } catch (MissingFlagException e1) {
            assertFalse(false);
        }
        int goodCapacityOccupied = warehouse.getCapacityOccupied();
        assertNotNull(goodCapacityOccupied);
        assertEquals(60, goodCapacityOccupied);
    }

}
