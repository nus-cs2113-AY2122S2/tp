package seedu.duke.command.itemcommand;

import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.EmptyItemNameException;
import seedu.duke.InvalidCommandException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.ItemList;

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateItemNameCommand extends Command {
    private static final String DELIMITER = "/";
    private String oldItemName;
    private String newItemName;
    private static final int NUMBER_OF_PARTS_IN_COMMAND = 2;
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    public UpdateItemNameCommand(String userInput) throws HotelLiteManagerException {
        if (userInput.startsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "The item name of the item to update is empty for the Update Item Name "
                    + "Command. Exception thrown.");
            throw new EmptyItemNameException();
        }
        if (userInput.endsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "The new item name of the item to update is empty for the Update Item "
                    + "Name Command. Exception thrown");
            throw new EmptyItemNameException();
        }
        StringTokenizer tokens = new StringTokenizer(userInput, DELIMITER);
        if (tokens.countTokens() != NUMBER_OF_PARTS_IN_COMMAND) {
            itemLogger.log(Level.WARNING, "Invalid formatting for the Update Item Name Command detected."
                    + " Exception thrown.");
            throw new InvalidCommandException();
        }
        String oldItemName = extractCurrentItemName(tokens);
        String newItemName = extractNewItemName(tokens);
        setOldItemName(oldItemName);
        setNewItemName(newItemName);
    }

    private String extractCurrentItemName(StringTokenizer tokens) throws HotelLiteManagerException {
        String currentItemName = tokens.nextToken();
        currentItemName = currentItemName.trim();
        if (currentItemName.isEmpty()) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for the item whose name we want to update"
                    + "in the Update Item Name Command. Exception thrown.");
            throw new EmptyItemNameException();
        }
        return currentItemName;
    }

    private String extractNewItemName(StringTokenizer tokens) throws HotelLiteManagerException {
        String newItemName = tokens.nextToken();
        newItemName = newItemName.trim();
        if (newItemName.isEmpty()) {
            itemLogger.log(Level.WARNING, "The new name of the item whose name we want to update is empty"
                    + "in the Update Item Name Command. Exception thrown.");
            throw new EmptyItemNameException();
        }
        return newItemName;
    }

    public String getOldItemName() {
        return oldItemName;
    }

    public void setOldItemName(String oldItemName) {
        this.oldItemName = oldItemName;
    }

    public String getNewItemName() {
        return newItemName;
    }

    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        ItemList listOfItems = listContainer.getItemList();
        String oldItemName = getOldItemName();
        String newItemName = getNewItemName();
        listOfItems.updateItemNameInList(oldItemName, newItemName);
        oldItemName = oldItemName.toUpperCase();
        newItemName = newItemName.toUpperCase();
        ui.printUpdateItemNameAcknowledgementMessage(oldItemName, newItemName);
    }
}
