package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.MissingFlagException;
import util.exceptions.EmptyFieldException;
import util.exceptions.WrongCommandException;

public class FulfillParser extends CommandParser {
    public FulfillParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void initExtractParams() throws MissingFlagException, EmptyFieldException {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "oid/(?<oid>\\d*)";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    @Override
    protected void extractParams() throws WrongCommandException {
        warehouse.fulfillOrder(matches.get("oid"));
    }
}
