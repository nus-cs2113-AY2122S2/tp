package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.EmptyKeywordException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.itemlists.ItemList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to search for items within the item list that matches a specific keyword. An SearchItemCommand
 * object consists of the keyword used to search for items within the item list.
 */
public class SearchItemCommand extends Command {
    private String keyword;
    private static final String SEARCH_ITEM_COMMAND = "search item";
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the search item command within the user input is
     * valid.
     * Takes the user input and creates a SearchItemCommand object using it.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the keyword is empty or if the string userInput contains "search item".
     */
    public SearchItemCommand(String userInput) throws HotelLiteManagerException {
        String keyword = userInput.trim();
        if (keyword.contains(SEARCH_ITEM_COMMAND)) {
            itemLogger.log(Level.WARNING, "Repeated search item command given.");
            throw new DuplicateCommandException();
        }

        if (keyword.isEmpty()) {
            throw new EmptyKeywordException();
        }
        setKeyword(keyword);
    }

    /**
     * Searches the item list for items whose name contains the keyword specified by the user.
     * Prints out all the items whose name contains the keyword specified by the user.
     * If no items are found, a message informing the user that there are no item in the item list whose name contains
     * the keyword would be displayed.
     *
     * @param listContainer The object containing the data structure necessary to search for items within the item
     *                      list.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        ItemList listOfItems = listContainer.getItemList();
        String keyword = getKeyword();
        ItemList listOfMatchingItems = listOfItems.findItemsInList(keyword);
        int numberOfItemsFound = listOfMatchingItems.getSize();
        if (numberOfItemsFound == 0) {
            ui.printNoItemsFoundInListAcknowledgementMessage();
        } else {
            ui.printItemList(listOfMatchingItems);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
