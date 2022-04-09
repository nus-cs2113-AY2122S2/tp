package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.stubs.ItemStubs.*;

import seedu.duke.stubs.ItemStubs;
import seedu.duke.stubs.UiStub;

public class SearchCommandTest {

    // ItemList Stubs for use in SearchCommand
    private static ItemList SEARCH_LIST;
    private static List<Item> SEARCH_RESULT_PAPER_NAME;
    private static List<Item> SEARCH_RESULT_DRAW_DESCRIPTION;
    private static List<Item> SEARCH_RESULT_PAPER_NAME_A4_DESCRIPTION;
    private static List<Item> SEARCH_RESULT_NONE;

    @BeforeAll
    public static void generateStubs() {
        SEARCH_LIST = generateItemList(ITEM_PAPER_A4_10, ITEM_PAPER_A5_10,
                ITEM_MARKER, ITEM_WHITEBOARD);
        SEARCH_RESULT_PAPER_NAME = generateImmutableResults(
                ITEM_PAPER_A4_10, ITEM_PAPER_A5_10);
        SEARCH_RESULT_DRAW_DESCRIPTION = generateImmutableResults(
                ITEM_MARKER, ITEM_WHITEBOARD);
        SEARCH_RESULT_PAPER_NAME_A4_DESCRIPTION = generateImmutableResults(ITEM_PAPER_A4_10);
        SEARCH_RESULT_NONE = generateImmutableResults();
    }

    @Test
    public void execute_givenDoesNotExist_matchNone() {
        UiStub uiStub = new UiStub();
        SearchCommand testPaperNameCommand = new SearchCommand(Optional.of("garbage"), Optional.empty());
        testPaperNameCommand.execute(SEARCH_LIST, uiStub);
        assertEquals(SEARCH_RESULT_NONE, testPaperNameCommand.getResults());
    }

    @Test
    public void execute_givenExists_matchGivenOnly() {
        // Expect two Paper Item with description A4 (Index 1) and A5 (Index 2)
        UiStub uiStub = new UiStub();
        SearchCommand testPaperNameCommand = new SearchCommand(Optional.of(ItemStubs.PAPER_NAME), Optional.empty());
        testPaperNameCommand.execute(SEARCH_LIST, uiStub);
        assertEquals(SEARCH_RESULT_PAPER_NAME, testPaperNameCommand.getResults(),
                SEARCH_LIST.getItemArrayList().toString());

        // Expect Paper Item with description A4 only (Index 1)
        uiStub = new UiStub();
        SearchCommand testPaperNameAndDescriptionCommand = new SearchCommand(Optional.of(ItemStubs.PAPER_NAME),
                Optional.of(ItemStubs.A4_PAPER_DESCRIPTION));
        testPaperNameAndDescriptionCommand.execute(SEARCH_LIST, uiStub);
        assertEquals(SEARCH_RESULT_PAPER_NAME_A4_DESCRIPTION,
                testPaperNameAndDescriptionCommand.getResults());

        // Expect Marker (Index 1) and Whiteboard Item (Index 2)
        uiStub = new UiStub();
        SearchCommand testDrawDescriptionCommand = new SearchCommand(Optional.empty(),
                Optional.of("draw"));
        testDrawDescriptionCommand.execute(SEARCH_LIST, uiStub);
        assertEquals(SEARCH_RESULT_DRAW_DESCRIPTION,testDrawDescriptionCommand.getResults());
    }

    private static ItemList generateItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        for (Item item: items) {
            list.add(Item.copyItem(item));
        }
        return new ItemList(list);
    }

    /**
     * Generates an expected list of items that would represent the result.
     * This is typically used to compare against commands that do not modify ItemList. E.g. List, Search, Desc.
     * @return
     */
    private static List<Item> generateImmutableResults(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        for (Item item: items) {
            list.add(Item.copyItem(item));
        }
        return List.copyOf(list);
    }
}
