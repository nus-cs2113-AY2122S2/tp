package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

public class TotalParser extends CommandParser{
    public TotalParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params(){
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[og])/ id/(?<id>\\d*)";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    };
    protected void extract_params() throws WrongCommandException, NullException {
        if (matches.get("flag").equals("o")) {
            // get total orders with flag "o/"
            int totalOrders = warehouse.totalOrder();
            System.out.printf("There are %d goods in total.\n", totalOrders);
        } else if (matches.get("flag").equals("g")) {
            // get total goods with flag "g/"
            warehouse.totalInventoryVol();
        } else {
            // wrong command exception
            throw new WrongCommandException("total", true);
        }
    };
}
