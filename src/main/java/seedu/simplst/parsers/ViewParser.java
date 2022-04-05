package seedu.simplst.parsers;

import seedu.simplst.Regex;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

public class ViewParser extends CommandParser {
    public ViewParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params() {
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})/ sku/(?<sku>.*)";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    }

    protected void extract_params() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            // view order with flag "o/"
            warehouse.viewOrderById(matches.get("id"));
        } else if (matches.get("flag").equals("g")) {
            // view inventory good with flag "g/"
            warehouse.viewGoodBySku(matches.get("id"));
        } else if (matches.get("flag").equals("ug")) {
            warehouse.viewUnitGood(matches.get("sku"));
        } else {
            // wrong command exception
            throw new WrongCommandException("view", true);
        }
    }
}
