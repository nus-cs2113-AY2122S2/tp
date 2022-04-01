package seedu.simplst.parsers;

import seedu.simplst.Regex;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

public class FindParser extends CommandParser {

    public FindParser(Warehouse warehouse) {
        super(warehouse);
    }

    @Override
    protected void init_extract_params() {
        Regex regexMatch;
        String regex;
        regex = "n/(?<name>.*)";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    }

    @Override
    protected void extract_params() throws WrongCommandException {
        if (matches.get("name").equals("")) {
            throw new WrongCommandException("find", true);
        }
        warehouse.findGoods(matches.get("name"));
    }
}
