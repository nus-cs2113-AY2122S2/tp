package seedu.simplst;

import seedu.simplst.parsers.AddParser;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.WrongCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;


//whatIsBeingTested_descriptionOfTestInputs_expectedOutcome

public class AddParserTest{
    Warehouse warehouse = new Warehouse(1000);
    String regex = "(?<flag>[ugbo]{1,2})/";
    AddParser addParser = new AddParser(warehouse);

    public void uniGoodFlag_addGood_matchInput() throws WrongCommandException, InvalidFileException, InvalidObjectType {
        String userInput = "add ug/ sku/WC1 n/Wooden Chair d/German oak qty/30";
        MatchKeywords matchKeywordsMatch;
        matchKeywordsMatch = new MatchKeywords(userInput, regex);
        HashMap<String, String> matches = matchKeywordsMatch.getGroupValues();
        addParser.extractParams();
        assertEquals("WC1", regexGoodMatch.get("sku"));
        }
    }

}
