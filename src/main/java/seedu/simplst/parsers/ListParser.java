package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class ListParser extends CommandParser {
    public ListParser(Warehouse warehouse) {
        super(warehouse);
    }

    public ListParser(Warehouse warehouse, String userInput) {
        super(warehouse);
        this.userInput = userInput;
    }

    @Override
    public void initExtractParams() throws MissingFlagException, EmptyFieldException {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})/";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    @Override
    public void extractParams() throws WrongCommandException, MissingFlagException, EmptyFieldException {
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
