package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.WrongCommandException;

public class ListParser extends CommandParser{
    public ListParser(Warehouse warehouse) {
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
            // list orders with flag "o/"
            this.warehouse.listOrders();
        } else if (matches.get("flag").equals("g")) {
            // list goods with flag "g/"
            this.warehouse.listGoods();
        } else {
            // wrong command exception
            throw new WrongCommandException("list", true);
        }
    };
}
