package seedu.simplst;

import org.junit.jupiter.api.Test;


import seedu.simplst.parsers.AddParser;
import util.exceptions.WrongCommandException;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.MissingFlagException;
import util.exceptions.EmptyFieldException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


//whatIsBeingTested_descriptionOfTestInputs_expectedOutcome

public class AddParserTest {

    Warehouse warehouse = new Warehouse(1000);
    String regex = "(?<flag>[ugbo]{1,2})/";


    @Test
    public void unitGoodAndGoodFlag_addGood_matchInput() throws WrongCommandException, InvalidFileException,
            InvalidObjectType, MissingFlagException, EmptyFieldException {
        String userInput = "add ug/ sku/WC1 n/Wooden Chair d/German oak size/Medium";
        String userInputTwo = "add g/ sku/WC1 qty/30";
        AddParser addParser = new AddParser(warehouse, userInput);
        AddParser addParserTwo = new AddParser(warehouse, userInputTwo);
        addParser.initExtractParams();
        addParser.extractParams();
        addParserTwo.initExtractParams();
        addParserTwo.extractParams();
        UnitGood ug = warehouse.getUnitGoodHashMap().get("WC1");

        assertNotNull(ug);
        assertEquals("WC1", ug.getSku());
        assertEquals(Capacity.MEDIUM, ug.getCapacity());
        assertEquals("Wooden Chair", ug.getName());
        assertEquals("German oak", ug.getDescription());

        Good g = warehouse.getGoodList().get("WC1");
        assertNotNull(g);
        assertEquals("WC1", g.getSku());
        assertEquals(30, g.getQuantity());
    }

    @Test
    public void orderFlagAddGoodMatchInput() throws WrongCommandException, InvalidFileException, InvalidObjectType,
            MissingFlagException, EmptyFieldException {
        String userInput = "add o/ oid/1 r/John Doe addr/123 Maple Ave";
        AddParser addParser = new AddParser(warehouse, userInput);
        addParser.initExtractParams();
        addParser.extractParams();
        ArrayList<Order> o = warehouse.getOrderLists();
        assertNotNull(o);
        assertEquals(1, o.get(0).getId());
        assertEquals("John Doe", o.get(0).getReceiver());
        assertEquals("123 Maple Ave", o.get(0).getShippingAddress());
    }

}

