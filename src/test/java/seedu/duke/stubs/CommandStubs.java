package seedu.duke.stubs;

import seedu.duke.commands.SearchCommand;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static seedu.duke.stubs.ItemStubs.ITEM_MARKER;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A5;
import static seedu.duke.stubs.ItemStubs.ITEM_WHITEBOARD;

public class CommandStubs {

    public static final ItemList LIST_TO_SEARCH = generateStubSearchList(ITEM_PAPER_A4, ITEM_PAPER_A5, ITEM_MARKER, ITEM_WHITEBOARD);
    public static final Set<String> RESULT_SEARCH_PAPER_NAME = generateSearchResult(ITEM_PAPER_A4, ITEM_PAPER_A5);
    public static final Set<String> RESULT_SEARCH_DRAW_DESCRIPTION = generateSearchResult(ITEM_MARKER, ITEM_WHITEBOARD);
    public static final Set<String> RESULT_SEARCH_PAPER_NAME_A4_DESCRIPTION = generateSearchResult(ITEM_PAPER_A4);
    public static final Set<String> RESULT_SEARCH_NONE = generateSearchResult();

    private static ItemList generateStubSearchList(Item... items) {
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
