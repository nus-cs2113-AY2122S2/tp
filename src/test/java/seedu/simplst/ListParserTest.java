package seedu.simplst;

import org.junit.jupiter.api.Test;
import seedu.simplst.parsers.AddParser;
import seedu.simplst.parsers.ListParser;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;
import util.exceptions.InvalidObjectType;
import util.exceptions.WrongCommandException;
import util.exceptions.InvalidFileException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ListParserTest {
    Warehouse warehouse = new Warehouse(1000);

    @Test //
    public void exceptions_missingFlag_catchException() throws MissingFlagException,
            EmptyFieldException, WrongCommandException, InvalidFileException, InvalidObjectType {
        String userInput = "list";
        ListParser listParser = new ListParser(warehouse, userInput);
        try {
            listParser.initExtractParams();
        } catch (MissingFlagException e) {
            assertTrue(true);
        }
    }

    @Test //
    public void exceptions_CorrectFlag_noException() throws MissingFlagException,
            EmptyFieldException, WrongCommandException, InvalidFileException, InvalidObjectType {
        String userInput = "list o/";
        ListParser listParser = new ListParser(warehouse, userInput);
        try {
            listParser.initExtractParams();
        } catch (MissingFlagException e) {
            assertFalse(false);
        }

        try {
            listParser.extractParams();
        } catch (WrongCommandException e1) {
            assertFalse(false);
        }
    }

}
