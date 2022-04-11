package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;
import util.exceptions.WrongCommandException;

public class FindParser extends CommandParser {

    public FindParser(Warehouse warehouse) {
        super(warehouse);
    }

    public FindParser(Warehouse warehouse, String userInput) {
        super(warehouse);
        this.userInput = userInput;
    }

    @Override
    public void initExtractParams() throws MissingFlagException, EmptyFieldException {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "n/(?<name>.*)";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    @Override
    public void extractParams() throws WrongCommandException {
        if (matches.get("name").equals("")) {
            throw new WrongCommandException("find", true);
        }
        warehouse.findGoods(matches.get("name"));
    }
}
