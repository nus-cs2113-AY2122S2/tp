package seedu.duke.parser;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DescCommand;
import seedu.duke.data.Item;

public class ParserStubs {
    public static final String PAPERCUP_NAME = "Paper Cup";
    public static final String PAPERCUP_QUANTITY = "25";
    public static final String PAPERCUP_DESCRIPTION = "25ml cups";

    public static final Item PAPERCUP_ITEM = new Item(ParserStubs.PAPERCUP_NAME,
            Integer.parseInt(PAPERCUP_QUANTITY), ParserStubs.PAPERCUP_DESCRIPTION);
    public static final AddCommand PAPERCUP_ADDCOMMAND = new AddCommand(PAPERCUP_ITEM);
    public static final DeleteCommand ZEROINDEX_DELETECOMMAND = new DeleteCommand(0);
    public static final DescCommand ZEROINDEX_DESCCOMMAND = new DescCommand(0);

}

