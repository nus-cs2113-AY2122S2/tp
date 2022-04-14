package seedu.simplst.parsers;

import seedu.simplst.Display;
import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;

public class HelpParser extends CommandParser {

    public HelpParser(Warehouse warehouse) {
        super(warehouse);
    }

    //for testing
    public HelpParser(Warehouse warehouse, String userInput) {
        super(warehouse);
        this.userInput = userInput;
    }

    boolean isNoFlag = false; //is set when user inputs help without flags

    public boolean isNoFlag() {
        return isNoFlag;
    }

    @Override
    public void initExtractParams() throws MissingFlagException, EmptyFieldException {
        if (!this.userInput.equalsIgnoreCase("help")) { //for help commands with flag
            MatchKeywords matchKeywordsMatch;
            String regex;
            regex = " (?<flag>[uog]{1,2})$";
            matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
            this.matches = matchKeywordsMatch.getGroupValues();
        } else {
            isNoFlag = true;
        }
    }

    @Override
    protected void extractParams() {
        if (isNoFlag) {
            Display.help();
        } else {
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
                System.out.println("Refer to user guide for instructions on help command flags.");
                Display.help();
            }
        }
        isNoFlag = false; //reset
    }
}
