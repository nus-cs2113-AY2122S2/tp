package seedu.simplst;

import org.junit.jupiter.api.Test;
import seedu.simplst.parsers.HelpParser;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


//whatIsBeingTested_descriptionOfTestInputs_expectedOutcome

public class HelpParserTest {
    Warehouse warehouse = new Warehouse(1000);
    String regex = "(?<flag>[uog]{1,2})";

    @Test
    public void withoutFlag_help_isNoFlagTrue() throws MissingFlagException, EmptyFieldException {
        String userInput = "help";
        HelpParser helpParser = new HelpParser(warehouse, userInput);

        helpParser.initExtractParams();
        assertTrue(helpParser.isNoFlag());
    }

    @Test
    public void withFlag_help_isNoFlagFalse() throws MissingFlagException, EmptyFieldException {
        String userInput = "help /o";
        HelpParser helpParser = new HelpParser(warehouse, userInput);

        helpParser.initExtractParams();
        assertFalse(helpParser.isNoFlag());
    }

}
