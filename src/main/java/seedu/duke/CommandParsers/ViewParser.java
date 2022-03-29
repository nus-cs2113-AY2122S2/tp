package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.WrongCommandException;

public class ViewParser extends CommandParser{
    public ViewParser(Warehouse warehouse) {
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
            // view order with flag "o/"
            warehouse.viewOrderById(matches.get("id"));
        } else if (matches.get("flag").equals("g")) {
            // view inventory good with flag "g/"
            warehouse.viewGoodBySKU(matches.get("id"));
        } else {
            // wrong command exception
            throw new WrongCommandException("view", true);
        }
    };
}
