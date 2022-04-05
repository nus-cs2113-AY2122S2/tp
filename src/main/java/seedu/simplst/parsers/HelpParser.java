package seedu.simplst.parsers;

import seedu.simplst.Display;
import seedu.simplst.Regex;
import seedu.simplst.Warehouse;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

public class HelpParser extends CommandParser {

    public HelpParser(Warehouse warehouse) {
        super(warehouse);
    }

    @Override
    protected void init_extract_params() {
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    }

    @Override
    protected void extract_params() throws WrongCommandException {
        String flag = matches.get("flag");
        switch (flag) {
        case "ug":
            Display.helpUnitGood();
            break;
        case "g":
            Display.helpGood();
            break;
        case "o":
            Display.helpOrder();
            break;
        default:
            Display.help();
        }
    }
}
