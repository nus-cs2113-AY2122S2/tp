package seedu.duke.stubs;

import seedu.duke.commands.SearchCommand;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static seedu.duke.stubs.ItemStubs.ITEM_MARKER;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4_10;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4_15;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A5_10;
import static seedu.duke.stubs.ItemStubs.ITEM_PENCIL;
import static seedu.duke.stubs.ItemStubs.ITEM_WHITEBOARD;

public class CommandStubs {

    public static final ItemList SEARCH_LIST = generateItemList(ITEM_PAPER_A4_10, ITEM_PAPER_A5_10, ITEM_MARKER,
            ITEM_WHITEBOARD);
    public static final Set<String> SEARCH_RESULT_PAPER_NAME = generateSearchResult(ITEM_PAPER_A4_10, ITEM_PAPER_A5_10);
    public static final Set<String> SEARCH_RESULT_DRAW_DESCRIPTION = generateSearchResult(ITEM_MARKER, ITEM_WHITEBOARD);
    public static final Set<String> SEARCH_RESULT_PAPER_NAME_A4_DESCRIPTION = generateSearchResult(ITEM_PAPER_A4_10);
    public static final Set<String> SEARCH_RESULT_NONE = generateSearchResult();


    public static final ItemList EDIT_NEGATIVE_QUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_NAME_LIST_BEFORE = generateItemList(ITEM_PENCIL);
    public static final ItemList EDIT_NAME_EXPECTED_LIST_AFTER = generateItemList(ITEM_MARKER);
    public static final ItemList EDIT_ABSQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_ABSQUANTITY_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A4_15);
    public static final ItemList EDIT_RELQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_15);
    public static final ItemList EDIT_RELQUANTITY_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_DESC_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
    public static final ItemList EDIT_DESC_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A5_10);
    public static final ItemList EDIT_ALL_LIST_BEFORE = generateItemList(ITEM_MARKER);
    public static final ItemList EDIT_ALL_EXPECTED_LIST_AFTER = generateItemList(ITEM_WHITEBOARD);

    public static final ItemList TEST_ITEM_LIST = generateItemList(ITEM_PAPER_A4_10, ITEM_PAPER_A5_10, ITEM_MARKER,
            ITEM_WHITEBOARD);

    private static ItemList generateItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        Collections.addAll(list, items);
        return new ItemList(list);
    }

    /**
     * Generates the expected print message after SearchCommand executes and matches {@code items}.
     * Note that order of items in the parameter is important since SearchCommand has indexing in its print messages.
     * @param items variable number of items to be added to the expected print message
     * @return a collection representing the expected output message
     */
    private static Set<String> generateSearchResult(Item... items) {
        Set<String> expectedMessages = new HashSet<>();
        expectedMessages.add(SearchCommand.SEARCH_RESULT_PREAMBLE);
        for (int i = 0; i < items.length; i++) {
            String printMsg = String.format(SearchCommand.SEARCH_RESULT_ENTRY_FORMAT, i, items[i]);
            expectedMessages.add(printMsg);
        }
        return expectedMessages;
    }
}
