package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.CommandStubs;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.duke.stubs.ItemStubs;
import seedu.duke.stubs.UiStub;

public class SearchCommandTest {

    @Test
    public void execute_givenDoesNotExist_matchNone() {
        UiStub uiStub = new UiStub();
        SearchCommand testPaperNameCommand = new SearchCommand(Optional.of("garbage"), Optional.empty());
        testPaperNameCommand.execute(CommandStubs.SEARCH_LIST, uiStub);
        assertEquals(CommandStubs.SEARCH_RESULT_NONE, testPaperNameCommand.getResults());
    }

    @Test
    public void execute_givenExists_matchGivenOnly() {
        // Expect two Paper Item with description A4 (Index 1) and A5 (Index 2)
        UiStub uiStub = new UiStub();
        SearchCommand testPaperNameCommand = new SearchCommand(Optional.of(ItemStubs.PAPER_NAME), Optional.empty());
        testPaperNameCommand.execute(CommandStubs.SEARCH_LIST, uiStub);
        /**
         *         assertEquals(CommandStubs.SEARCH_RESULT_PAPER_NAME, testPaperNameCommand.getResults());.
         * */

        // Expect Paper Item with description A4 only (Index 1)
        uiStub = new UiStub();
        SearchCommand testPaperNameAndDescriptionCommand = new SearchCommand(Optional.of(ItemStubs.PAPER_NAME),
                Optional.of(ItemStubs.A4_PAPER_DESCRIPTION));
        testPaperNameAndDescriptionCommand.execute(CommandStubs.SEARCH_LIST, uiStub);
        /**
         * assertEquals(CommandStubs.SEARCH_RESULT_PAPER_NAME_A4_DESCRIPTION, testPaperNameAndDescriptionCommand.
         * getResults());.
         * */

        // Expect Marker (Index 1) and Whiteboard Item (Index 2)
        uiStub = new UiStub();
        SearchCommand testDrawDescriptionCommand = new SearchCommand(Optional.empty(),
                Optional.of("draw"));
        testDrawDescriptionCommand.execute(CommandStubs.SEARCH_LIST, uiStub);
        /**
         *         assertEquals(CommandStubs.SEARCH_RESULT_DRAW_DESCRIPTION,testDrawDescriptionCommand.getResults());.
         * */
    }
}
