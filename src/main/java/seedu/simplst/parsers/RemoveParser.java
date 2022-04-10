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
            String regexOrder = "oid/(?<oid>)\\d";
            HashMap<String, String> regexOrderMatch = new
                    MatchKeywords(userInput, regexOrder).getGroupValues();
            warehouse.removeOrder(regexOrderMatch.get("oid"));
        } else if (matches.get("flag").equals("og")) {
            String regexOrderline = "oid/(?<oid>\\d*) sku/(?<sku>.*) q/(?<qty>\\d*)";
            HashMap<String, String> regexOrderlineMatch = new
                    MatchKeywords(userInput, regexOrderline).getGroupValues();
            warehouse.removeOrderline(regexOrderlineMatch.get("oid"),
                    regexOrderlineMatch.get("sku"), regexOrderlineMatch.get("qty"));
        } else if (matches.get("flag").equals("ug")) {
            String regexGood = "sku/(?<sku>.*)";
            HashMap<String, String> regexGoodMatch = new
                    MatchKeywords(userInput, regexGood).getGroupValues();

            String sku = regexGoodMatch.get("sku");
            warehouse.removeUnitGood(sku);
        } else if (matches.get("flag").equals("g")) {
            String regexGood = "sku/(?<sku>.*) qty/(?<quantity>.*)";
            HashMap<String, String> regexGoodMatch = new
                    MatchKeywords(userInput, regexGood).getGroupValues();

            String sku = regexGoodMatch.get("sku");
            String quantity = regexGoodMatch.get("quantity");
            warehouse.removeQuantityOfGoods(sku, quantity);
        } else {
            throw new WrongCommandException("remove", true);
        }
    }
}
