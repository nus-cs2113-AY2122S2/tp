package seedu.duke.storage;


import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.itemlists.ItemList;
import seedu.duke.itemlists.Item;
import seedu.duke.exceptions.ItemFileNotFoundException;
import seedu.duke.exceptions.InvalidItemPaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ItemListFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/ItemList.txt";

    /**
     * Returns the Item List which contains the contents stored within the file ListFolder/ItemList.txt
     *
     * @return the Item List containing the contents stored within the file ListFolder/ItemList.txt
     * @throws HotelLiteManagerException if the folder that the file is stored in does not exist and we are unable to
     *                                   create it, if the file specified by the file path does not exist and we are
     *                                   unable to create it or if the item pax of an item stored within the
     *                                   ListFolder/ItemList.txt file is not an integer.
     */

    public ItemList load() throws HotelLiteManagerException {
        ItemList listOfItems = new ItemList();
        Item item;
        File file = getFile(FILE_PATH);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new ItemFileNotFoundException();
        }

        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String itemName = splitData[0].trim();
            String itemPaxStringVersion = splitData[1].trim();
            int itemPax;
            try {
                itemPax = Integer.parseInt(itemPaxStringVersion);
            } catch (NumberFormatException e) {
                throw new InvalidItemPaxException();
            }
            item = new Item(itemName, itemPax);
            listOfItems.addItemToList(item);
        }
        return listOfItems;
    }

    /**
     * Deletes all the content currently stored within the file ListFolder/ItemList.txt
     *
     * @throws IOException if we are unable to write to the file ListFolder/ItemList.txt
     */
    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

    /**
     * Update the contents stored within the file ListFolder/ItemList.txt with the current content of the item list.
     *
     * @throws IOException if we are unable to write to the file ListFolder/ItemList.txt
     */
    public void writeItemListToFile(ItemList listOfItems) throws IOException {
        clearFileContents();
        FileWriter fw = new FileWriter(FILE_PATH, true);
        int lastIndexOfArrayList = listOfItems.getSize() - 1;
        String itemName;
        int itemPax;
        Item item;
        String itemDetails;
        for (int i = 0; i < listOfItems.getSize(); i++) {
            item = listOfItems.getItem(i);
            itemName = item.getName();
            itemPax = item.getPax();
            itemDetails = itemName + '|' + itemPax;
            if (i != lastIndexOfArrayList) {
                itemDetails = itemDetails + System.lineSeparator();
            }
            fw.write(itemDetails);
        }
        fw.close();
    }
}
