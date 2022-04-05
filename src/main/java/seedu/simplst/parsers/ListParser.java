package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class ListParser extends CommandParser {
    public ListParser(Warehouse warehouse) {
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
            // list orders with flag "o/"
            this.warehouse.listOrders();
        } else if (matches.get("flag").equals("og")) {
            String regexOrderline = "oid/(?<oid>\\d*)";
            HashMap<String, String> regexOrderlineMatch = new MatchKeywords(
                    userInput, regexOrderline).getGroupValues();
            warehouse.listOrderlines(regexOrderlineMatch.get("oid"));
        } else if (matches.get("flag").equals("g")) {
            // list goods with flag "g/"
            this.warehouse.listGoods(); // refers to inventory goods
        } else if (matches.get("flag").equals("ug")) {
            this.warehouse.listUnitGoods();
        } else {
            // wrong command exception
            throw new WrongCommandException("list", true);
        }
    }
}
