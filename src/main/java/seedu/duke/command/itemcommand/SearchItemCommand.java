package seedu.duke.command.itemcommand;

import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.EmptyKeywordException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.ItemList;

public class SearchItemCommand extends Command {
    private String keyword;

    public SearchItemCommand(String userInput) throws HotelLiteManagerException {
        String keyword = userInput.trim();
        if (keyword.isEmpty()) {
            throw new EmptyKeywordException();
        }
        setKeyword(keyword);
    }

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
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
