package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.WrongCommandException;

import java.util.HashMap;

public class AddParser extends CommandParser{
    public AddParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params(){
        Regex regexMatch;
        String regex;
        regex = "(?<flag>[ugbo]{1,2})/";
        regexMatch = new Regex(this.userInput, regex);
        this.matches = regexMatch.getGroupValues();
    };
    protected void extract_params() throws WrongCommandException, InvalidFileException, InvalidObjectType {
        if (matches.get("flag").equals("g")) {
            String regexGood = "sku/(?<sku>.*) n/(?<name>.*) d/(?<desc>.*) up/(?<up>.*) ui/(?<ui>.*) wu/(?<isWholeUnit>.*) ba/(?<ba>.*)" +
                    " v/(?<v>.*) ip/(?<ip>.*) qty/(?<qty>.*)";
            HashMap<String,String> regexGoodMatch = new Regex(userInput, regexGood).getGroupValues();
            warehouse.addGoodToInventory(regexGoodMatch.get("sku"),regexGoodMatch.get("name"),
                    regexGoodMatch.get("desc"),
                    Float.parseFloat(regexGoodMatch.get("up")), regexGoodMatch.get("ui"),
                    Boolean.parseBoolean(regexGoodMatch.get("isWholeUnit")),
                    Float.parseFloat(regexGoodMatch.get("ba")),
                    Float.parseFloat(regexGoodMatch.get("v")),
                    Boolean.parseBoolean(regexGoodMatch.get("ip")),
                    Float.parseFloat(regexGoodMatch.get("qty")));
        } else if (matches.get("flag").equals("ug")){
            String regexUnitGood = "sku/(?<sku>.*) n/(?<name>.*) d/(?<desc>.*) up/(?<up>.*) ui/(?<ui>.*) wu/(?<isWholeUnit>.*) ba/(?<ba>.*)" +
                    " v/(?<v>.*) ip/(?<ip>.*)";
            HashMap<String,String> regexUnitGoodMatch = new
                    Regex(userInput, regexUnitGood).getGroupValues();
            warehouse.addUnitGoodToInventory(regexUnitGoodMatch.get("sku"),regexUnitGoodMatch.get("name"),
                    regexUnitGoodMatch.get("desc"),
                    Float.parseFloat(regexUnitGoodMatch.get("up")), regexUnitGoodMatch.get("ui"),
                    Boolean.parseBoolean(regexUnitGoodMatch.get("isWholeUnit")),
                    Float.parseFloat(regexUnitGoodMatch.get("ba")),
                    Float.parseFloat(regexUnitGoodMatch.get("v")),
                    Boolean.parseBoolean(regexUnitGoodMatch.get("ip")));
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

    };


}
