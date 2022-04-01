package seedu.duke.CommandParsers;

import seedu.duke.Regex;
import seedu.duke.Warehouse;
import util.exceptions.*;

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
            try {
                warehouse.addGoodToInventory(regexGoodMatch.get("sku"),regexGoodMatch.get("qty"));
            } catch (ItemDoesNotExistException e) {
                // Can change to automatically add a unit good
                System.out.println("Unit Good does not exist. Please add a Unit Good first");
            }
        } else if (matches.get("flag").equals("ug")){
            String regexUnitGood = "sku/(?<sku>.*) n/(?<name>.*) d/(?<desc>.*) up/(?<up>.*) ui/(?<ui>.*) wu/(?<isWholeUnit>.*) ba/(?<ba>.*)" +
                    " v/(?<v>.*) ip/(?<ip>.*)";
            HashMap<String,String> regexUnitGoodMatch = new
                    Regex(userInput, regexUnitGood).getGroupValues();
            try {
                warehouse.addUnitGoodToInventory(regexUnitGoodMatch.get("sku"),regexUnitGoodMatch.get("name"),
                        regexUnitGoodMatch.get("desc"), regexUnitGoodMatch.get("capacity"));
            } catch (UnitTestException e) {
                System.out.println("Capacity Added is not either Small, Medium, Large. "
                        + "Default set to Medium");
            }
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
