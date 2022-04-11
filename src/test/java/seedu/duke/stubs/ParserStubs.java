package seedu.duke.stubs;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DescCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.BorrowCommand;
import seedu.duke.commands.ReturnCommand;
import seedu.duke.commands.LostCommand;
import seedu.duke.data.Item;

import java.time.LocalDate;
import java.util.Optional;

/* Contains various stubs and constants used for testing. */
public class ParserStubs {
    public static final String PAPERCUP_NAME = "Paper Cup";
    public static final String PAPERCUP_QUANTITY = "25";
    public static final String PAPERCUP_SETQUANTITY = "23";
    public static final int PAPERCUP_SETQUANTITY_INT = 23;
    public static final String PAPERCUP_SETRELATIVEQUANTITY = "2";
    public static final int PAPERCUP_SETRELATIVEQUANTITY_INT = 2;
    public static final String PAPERCUP_DESCRIPTION = "25ml cups";
    public static final String ITEM_INDEX = "1";
    public static final String ITEM_INDEX_OUT_OF_RANGE = "-1";
    public static final String NON_INT_ITEM_INDEX = "abc";
    public static final String BORROW_START_DATE_STR = "2022-03-21";
    public static final LocalDate BORROW_START_DATE = LocalDate.parse("2022-03-21");
    public static final String BORROW_END_DATE_STR = "2022-03-23";
    public static final LocalDate BORROW_END_DATE = LocalDate.parse("2022-03-23");
    public static final String BORROWER_NAME = "John";

    public static final Item PAPERCUP_ITEM = new Item(ParserStubs.PAPERCUP_NAME,
            Integer.parseInt(PAPERCUP_QUANTITY), ParserStubs.PAPERCUP_DESCRIPTION);
    public static final AddCommand PAPERCUP_ADDCOMMAND = new AddCommand(PAPERCUP_ITEM);
    public static final DeleteCommand ZEROINDEX_DELETECOMMAND = new DeleteCommand(0);
    public static final DescCommand ZEROINDEX_DESCCOMMAND = new DescCommand(0);
    public static final EditCommand ZEROINDEX_EDITCOMMAND_QUANTITYONLY = new EditCommand(0,
            Optional.empty(),
            Optional.of(PAPERCUP_SETQUANTITY_INT),
            Optional.empty(),
            Optional.empty());
    public static final EditCommand ZEROINDEX_EDITCOMMAND_NEGATIVERELATIVEQUANTITY = new EditCommand(0,
            Optional.empty(),
            Optional.of(PAPERCUP_SETRELATIVEQUANTITY_INT),
            Optional.empty(),
            Optional.of(false));
    public static final EditCommand ZEROINDEX_EDITCOMMAND_NAMEDESC_MISSINGQUANTITY = new EditCommand(0,
            Optional.of(PAPERCUP_NAME),
            Optional.empty(),
            Optional.of(PAPERCUP_DESCRIPTION),
            Optional.empty());
    public static final SearchCommand SEARCHCOMMAND_NAMEONLY = new SearchCommand(
            Optional.of(PAPERCUP_NAME), Optional.empty());
    public static final SearchCommand SEARCHCOMMAND_DESCRIPTIONONLY = new SearchCommand(
            Optional.empty(), Optional.of(PAPERCUP_DESCRIPTION));
    public static final SearchCommand SEARCHCOMMAND_NAMEANDDESCRIPTION = new SearchCommand(
            Optional.of(PAPERCUP_NAME), Optional.of(PAPERCUP_DESCRIPTION));
    public static final BorrowCommand BORROWCOMMAND_ALL_FIELDS = new BorrowCommand(
            Integer.parseInt(ITEM_INDEX) - 1,
            Integer.parseInt(PAPERCUP_QUANTITY),
            BORROW_START_DATE,
            BORROW_END_DATE,
            BORROWER_NAME);
    public static final ReturnCommand RETURNCOMMAND = new ReturnCommand(Integer.parseInt(ITEM_INDEX) - 1);
    public static final LostCommand LOST_COMMAND = new LostCommand(Integer.parseInt(ITEM_INDEX) - 1, Integer.parseInt(PAPERCUP_QUANTITY));


}

