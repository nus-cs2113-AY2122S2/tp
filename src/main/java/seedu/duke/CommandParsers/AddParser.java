package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.InvalidFileException;
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
    protected void execute() throws WrongCommandException, InvalidFileException {
        if (matches.get("flag").equals("g")) {
            String regexGood = "oid/(?<oid>\\d*) gid/(?<gid>\\d*)"
                    + " n/(?<name>.*) q/(?<qty>\\d*) d/(?<desc>.*)";
            HashMap<String,String> regexGoodMatch = new
                    Regex(userInput, regexGood).getGroupValues();
            warehouse.addGoods(regexGoodMatch.get("oid"), regexGoodMatch.get("gid"),
                    regexGoodMatch.get("name"), regexGoodMatch.get("qty"), regexGoodMatch.get("desc"));
        } else if (matches.get("flag").equals("ug")){
            String regexUnitGood = "sku/(?<sku>.*) n/(?<name>.*) d/(?<desc>.*) up/(?<up>.*) ui/(?<ui>.*) wu/(?<isWholeUnit>.*) ba/(?<ba>.*)" +
                    " v/(?<v>.*) ip/(?<ip>.*)";
            HashMap<String,String> regexUnitGoodMatch = new
                    Regex(userInput, regexUnitGood).getGroupValues();
            warehouse.addUnitGoodToInventory(regexUnitGoodMatch.get("sku"),regexUnitGoodMatch.get("name"), regexUnitGoodMatch.get("desc"),
                    regexUnitGoodMatch.get("up"), regexUnitGoodMatch.get("ui"), regexUnitGoodMatch.get("isWholeUnit"),regexUnitGoodMatch.get("ba"),
                    regexUnitGoodMatch.get("v"), regexUnitGoodMatch.get("ip"));
        } else if (matches.get("flag").equals("bg")){
            // batch goods
            String regexBatchGoods = "fp/(?<filepath>.*)";
            HashMap<String,String> regexBatchGoodsMatch = new
                    Regex(userInput, regexBatchGoods).getGroupValues();
            warehouse.batchSetGoodsToInventory(regexBatchGoodsMatch.get("filepath"));
        } else if (matches.get("flag").equals("bo")){
            // batch goods
            String regexBatchGoods = "fp/(?<filepath>.*)";
            HashMap<String,String> regexBatchGoodsMatch = new
                    Regex(userInput, regexBatchGoods).getGroupValues();
            warehouse.batchSetOrders(regexBatchGoodsMatch.get("filepath"));
        }

        else {
            throw new WrongCommandException("add", true);
        }

//        if (matches.get("flag").equals("o")) {
//            String regexOrder = "oid/(?<oid>\\d*) r/(?<recv>.*) a/(?<address>.*)";
//            HashMap<String,String> regexOrderMatches = new
//                    Regex(userInput, regexOrder).getGroupValues();
//            warehouse.addOrder(regexOrderMatches.get("oid"), regexOrderMatches.get("recv"),
//                    regexOrderMatches.get("address"));
//        } else
    };
}
