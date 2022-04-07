package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

public class ViewParser extends CommandParser {
    public ViewParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params() {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})/ sku/(?<sku>.*)";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    protected void extract_params() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            // view order with flag "o/"
            warehouse.viewOrderById(matches.get("id"));
        } else if (matches.get("flag").equals("g")) {
            // view inventory good with flag "g/"
            warehouse.viewGood(matches.get("sku"));
        } else {
            // wrong command exception
            throw new WrongCommandException("view", true);
        }
    }
}
