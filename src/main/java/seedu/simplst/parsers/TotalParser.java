package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

public class TotalParser extends CommandParser {
    public TotalParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void initExtractParams() throws MissingFlagException, EmptyFieldException {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[og])/ id/(?<id>\\d*)";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    protected void extractParams() throws WrongCommandException, NullException {
        if (matches.get("flag").equals("o")) {
            // get total orders with flag "o/"
            int totalOrders = warehouse.totalNumberOfOrder();
            System.out.printf("There are %d goods in total.\n", totalOrders);
        } else if (matches.get("flag").equals("g")) {
            // get total goods with flag "g/"
            warehouse.getCapacityOccupied();
        } else {
            // wrong command exception
            throw new WrongCommandException("total", true);
        }
    }
}
