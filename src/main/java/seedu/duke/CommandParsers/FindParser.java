package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.NullException;
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
