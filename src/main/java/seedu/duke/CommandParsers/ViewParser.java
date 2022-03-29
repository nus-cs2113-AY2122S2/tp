package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class ViewParser extends CommandParser{
    public ViewParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void extract_params(){
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[og])/ id/(?<id>\\d*)";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    };
    protected void execute() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            // view order with flag "o/"
            warehouse.viewOrder(matches.get("id"));
        } else if (matches.get("flag").equals("g")) {
            // view inventory good with flag "g/"
            warehouse.viewGood(matches.get("id"));
        } else {
            // wrong command exception
            throw new WrongCommandException("view", true);
        }
    };
}
