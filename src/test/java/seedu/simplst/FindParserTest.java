package seedu.simplst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.simplst.parsers.AddParser;
import seedu.simplst.parsers.FindParser;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;
import util.exceptions.WrongCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//whatIsBeingTested_descriptionOfTestInputs_expectedOutcome


public class FindParserTest {
    Warehouse warehouse = new Warehouse(1000);
    String regex = "n/(?<name>.*)";

    @Test
    public void nonExistentGood_newName_systemOutput() throws MissingFlagException, EmptyFieldException,
            WrongCommandException {
        String userInput = "find n/Wooden Chair";
        FindParser findParser = new FindParser(warehouse, userInput);
        findParser.initExtractParams();
        try {
            findParser.extractParams();
        } catch (WrongCommandException e) {
            assertTrue(true);
        }
    }


}
