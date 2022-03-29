package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class AddParser extends CommandParser{
    public AddParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void extract_params(){
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[og])/";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    };
    protected void execute() throws WrongCommandException {
        if (matches.get("flag").equals("o")) {
            String regexOrder = "oid/(?<oid>\\d*) r/(?<recv>.*) a/(?<address>.*)";
            HashMap<String,String> regexOrderMatches = new
                    Regex(userInput, regexOrder).getGroupValues();
            warehouse.addOrder(regexOrderMatches.get("oid"), regexOrderMatches.get("recv"),
                    regexOrderMatches.get("address"));
        } else if (matches.get("flag").equals("g")) {
            String regexGood = "oid/(?<oid>\\d*) gid/(?<gid>\\d*)"
                    + " n/(?<name>.*) q/(?<qty>\\d*) d/(?<desc>.*)";
            HashMap<String,String> regexGoodMatch = new
                    Regex(userInput, regexGood).getGroupValues();
            warehouse.addGoods(regexGoodMatch.get("oid"), regexGoodMatch.get("gid"),
                    regexGoodMatch.get("name"), regexGoodMatch.get("qty"), regexGoodMatch.get("desc"));
        } else {
            throw new WrongCommandException("add", true);
        }
    };
}
