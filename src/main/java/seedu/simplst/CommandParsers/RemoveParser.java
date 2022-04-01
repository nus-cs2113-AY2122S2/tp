package seedu.simplst.CommandParsers;

import seedu.simplst.Regex;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class RemoveParser extends CommandParser{
    public RemoveParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params(){
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[og])/ id/(?<id>\\d*)";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    };
    protected void extract_params() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            warehouse.removeOrder(matches.get("id"));
        } else if (matches.get("flag").equals("g")) {
            String regexGood = "id/(?<id>\\d*) q/(?<qty>\\d*)";
            HashMap<String,String> regexGoodMatch = new
                    Regex(userInput, regexGood).getGroupValues();

            String id = regexGoodMatch.get("id");
            if (regexGoodMatch.containsKey("qty")){
                warehouse.removeGoods(id, regexGoodMatch.get("qty"));
            } else {
                warehouse.removeGoods(id);
            }


        } else {
            throw new WrongCommandException("remove", true);
        }
    };
}
