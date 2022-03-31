package seedu.duke.stubs;

import seedu.duke.commands.SearchCommand;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static seedu.duke.stubs.ItemStubs.ITEM_MARKER;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4_10;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4_15;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A5_10;
import static seedu.duke.stubs.ItemStubs.ITEM_PENCIL;
import static seedu.duke.stubs.ItemStubs.ITEM_WHITEBOARD;

public class CommandStubs {

  /*  public static final ItemList SEARCH_LIST = generateItemList(ITEM_PAPER_A4_10, ITEM_PAPER_A5_10, ITEM_MARKER,
            ITEM_WHITEBOARD);
    public static final Set<Item> SEARCH_RESULT_PAPER_NAME = generateItemList(ITEM_PAPER_A4_10, ITEM_PAPER_A5_10);
    public static final Set<Item> SEARCH_RESULT_DRAW_DESCRIPTION = generateItemList(ITEM_MARKER, ITEM_WHITEBOARD);
    public static final Set<Item> SEARCH_RESULT_PAPER_NAME_A4_DESCRIPTION = generateItemList(ITEM_PAPER_A4_10);
    public static final Set<Item> SEARCH_RESULT_NONE = generateItemList();
*/

    public static final ItemList EDIT_NEGATIVE_QUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_NAME_LIST_BEFORE = generateItemList(ITEM_PENCIL);
    public static final ItemList EDIT_NAME_EXPECTED_LIST_AFTER = generateImmutableItemList(ITEM_MARKER);
    public static final ItemList EDIT_ABSQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_ABSQUANTITY_EXPECTED_LIST_AFTER = generateImmutableItemList(ITEM_PAPER_A4_15);
    public static final ItemList EDIT_RELQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_15);
    public static final ItemList EDIT_RELQUANTITY_EXPECTED_LIST_AFTER = generateImmutableItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_DESC_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_DESC_EXPECTED_LIST_AFTER = generateImmutableItemList(ITEM_PAPER_A5_10);
    public static final ItemList EDIT_ALL_LIST_BEFORE = generateItemList(ITEM_MARKER);
    public static final ItemList EDIT_ALL_EXPECTED_LIST_AFTER = generateImmutableItemList(ITEM_WHITEBOARD);

    private static ItemList generateItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        Collections.addAll(list, items);
        return new ItemList(list);
    }

    private static ItemList generateImmutableItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        Collections.addAll(list, items);
        return new ItemList(List.copyOf(list));
    }
}
