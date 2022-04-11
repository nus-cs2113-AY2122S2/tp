package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class ViewParser extends CommandParser {
    public ViewParser(Warehouse warehouse) {
        super(warehouse);
    }

    @Override
    protected void initExtractParams() throws MissingFlagException, EmptyFieldException {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})/";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    @Override
    protected void extractParams() throws WrongCommandException, MissingFlagException, EmptyFieldException {
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
