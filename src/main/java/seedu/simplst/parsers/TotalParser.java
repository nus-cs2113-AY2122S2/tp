package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

public class TotalParser extends CommandParser {
    public TotalParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params() {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[og])/ id/(?<id>\\d*)";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    protected void extract_params() throws WrongCommandException, NullException {
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
