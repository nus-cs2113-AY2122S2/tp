package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class RemoveParser extends CommandParser{
    public RemoveParser(Warehouse warehouse) {
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
            warehouse.removeOrder(matches.get("id"));
        } else if (matches.get("flag").equals("g")) {
            String regexGood = "id/(?<id>\\d*) q/(?<qty>\\d*)";
            HashMap<String,String> regexGoodMatch = new
                    Regex(userInput, regexGood).getGroupValues();
            warehouse.removeGoods(regexGoodMatch.get("id"), regexGoodMatch.get("qty"));
        } else {
            throw new WrongCommandException("remove", true);
        }
    };
}
