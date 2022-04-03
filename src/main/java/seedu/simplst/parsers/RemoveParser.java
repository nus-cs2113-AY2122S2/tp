package seedu.simplst.parsers;

import seedu.simplst.Regex;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class RemoveParser extends CommandParser {
    public RemoveParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params() {
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})/";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    }

    protected void extract_params() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            warehouse.removeOrder(matches.get("id"));
        } else if (matches.get("flag").equals("ug")) {
            String regexGood = "sku/(?<sku>.*)";
            HashMap<String, String> regexGoodMatch = new
                    Regex(userInput, regexGood).getGroupValues();

            String sku = regexGoodMatch.get("sku");
            warehouse.removeUnitGood(sku);
        } else {
            throw new WrongCommandException("remove", true);
        }
    }
}
