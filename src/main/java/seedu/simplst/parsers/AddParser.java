package seedu.simplst.parsers;

import seedu.simplst.MatchKeywords;
import seedu.simplst.Warehouse;
import util.exceptions.WrongCommandException;
import util.exceptions.MissingFlagException;
import util.exceptions.EmptyFieldException;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.UnitTestException;

import java.util.HashMap;

public class AddParser extends CommandParser {
    public AddParser(Warehouse warehouse) {
        super(warehouse);
    }

    protected void init_extract_params() throws MissingFlagException, EmptyFieldException {
        MatchKeywords matchKeywordsMatch;
        String regex;
        regex = "(?<flag>[ugbo]{1,2})/";
        matchKeywordsMatch = new MatchKeywords(this.userInput, regex);
        this.matches = matchKeywordsMatch.getGroupValues();
    }

    protected void extract_params() throws WrongCommandException, InvalidFileException, InvalidObjectType,
            MissingFlagException, EmptyFieldException {
        if (matches.get("flag").equals("g")) {
            String regexGood = "sku/(?<sku>.*) qty/(?<qty>.*)";
            HashMap<String, String> regexGoodMatch = new MatchKeywords(userInput, regexGood).getGroupValues();
            try {
                warehouse.addQuantityOfGoodToInventory(regexGoodMatch.get("sku"), regexGoodMatch.get("qty"));
            } catch (ItemDoesNotExistException e) {
                // Can change to automatically add a unit good
                System.out.println("Unit Good does not exist. Please add a Unit Good first");
            }
        } else if (matches.get("flag").equals("ug")) {
            String regexUnitGood = "sku/(?<sku>.*) n/(?<name>.*) d/(?<desc>.*) size/(?<size>.*)";
<<<<<<< HEAD
            HashMap<String, String> regexUnitGoodMatch = new MatchKeywords(userInput, regexUnitGood).getGroupValues();
            try {
                warehouse.addUnitGoodToInventory(regexUnitGoodMatch.get("sku"), regexUnitGoodMatch.get("name"),
                        regexUnitGoodMatch.get("desc"), regexUnitGoodMatch.get("size"));
            } catch (UnitTestException e) {
                System.out.println("Capacity Added is not either Small, Medium, Large. "
                        + "Default set to Medium");
            }
=======
            HashMap<String, String> regexUnitGoodMatch = new
                    MatchKeywords(userInput, regexUnitGood).getGroupValues();

            warehouse.addUnitGoodToInventory(regexUnitGoodMatch.get("sku"), regexUnitGoodMatch.get("name"),
                    regexUnitGoodMatch.get("desc"), regexUnitGoodMatch.get("size"));
>>>>>>> ee295f950b70b3622b13d514b93d6b34aeeee7e9
        } else if (matches.get("flag").equals("o")) {
            // adding the base details for order
            String regexOrder = "oid/(?<oid>\\d*) r/(?<recv>.*) addr/(?<addr>.*)";
            HashMap<String, String> regexOrderMatch = new MatchKeywords(
                    userInput, regexOrder).getGroupValues();
            warehouse.addOrder(regexOrderMatch.get("oid"),
                    regexOrderMatch.get("recv"), regexOrderMatch.get("addr"));
        } else if (matches.get("flag").equals("og")) {
            // adding a good for that order
            String regexOrderline = "oid/(?<oid>\\d*) sku/(?<sku>.*) q/(?<qty>\\d*)";
            HashMap<String, String> regexOrderlineMatch = new MatchKeywords(
                    userInput, regexOrderline).getGroupValues();
            warehouse.addOrderline(regexOrderlineMatch.get("oid"),
                    regexOrderlineMatch.get("sku"), regexOrderlineMatch.get("qty"));

        } else if (matches.get("flag").equals("bg")) {
            // batch goods
            String regexBatchGoods = "fp/(?<filepath>.*)";
            HashMap<String, String> regexBatchGoodsMatch = new
                    MatchKeywords(userInput, regexBatchGoods).getGroupValues();
            warehouse.batchSetGoodsToInventory(regexBatchGoodsMatch.get("filepath"));
        } else if (matches.get("flag").equals("bo")) {
            // batch goods
            String regexBatchGoods = "fp/(?<filepath>.*)";
            HashMap<String, String> regexBatchGoodsMatch = new
                    MatchKeywords(userInput, regexBatchGoods).getGroupValues();
            warehouse.batchSetOrders(regexBatchGoodsMatch.get("filepath"));
        } else {
            throw new WrongCommandException("add", true);
        }
    }
}
