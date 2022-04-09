package seedu.simplst.parsers;

import seedu.simplst.Display;
import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;

public class HelpParser extends CommandParser {

    public HelpParser(Warehouse warehouse) {
        super(warehouse);
    }

    @Override
    protected void init_extract_params() {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[uog]{1,2})";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    @Override
    protected void extract_params() {
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
