package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class RemoveParser extends CommandParser {
    public RemoveParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params() {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})/";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    protected void extract_params() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            warehouse.removeOrder(matches.get("id"));
        } else if (matches.get("flag").equals("ug")) {
            String regexGood = "sku/(?<sku>.*)";
            HashMap<String, String> regexGoodMatch = new
                    MatchKeywords(userInput, regexGood).getGroupValues();

            String sku = regexGoodMatch.get("sku");
            warehouse.removeUnitGood(sku);
        } else {
            throw new WrongCommandException("remove", true);
        }
    }
}
