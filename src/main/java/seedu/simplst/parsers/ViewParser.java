package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class ViewParser extends CommandParser {
    public ViewParser(Warehouse warehouse) {
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
            // view order with flag "o/"
            String regexOrder = "oid/(?<oid>\\d*)";
            HashMap<String, String> regexOrderMatch = new
                    MatchKeywords(userInput, regexOrder).getGroupValues();
            warehouse.viewOrderById(regexOrderMatch.get("oid"));
        } else if (matches.get("flag").equals("g")) {
            // view inventory good with flag "g/"
            String regexGood = "sku/(?<sku>.*)";
            HashMap<String, String> regexGoodMatch = new
                    MatchKeywords(userInput, regexGood).getGroupValues();
            warehouse.viewGood(regexGoodMatch.get("sku"));
        } else {
            // wrong command exception
            throw new WrongCommandException("view", true);
        }
    }
}
