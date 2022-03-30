package seedu.duke.storage;


import seedu.duke.HotelLiteManagerException;
import seedu.duke.ItemList;
import seedu.duke.Item;
import seedu.duke.ItemFileNotFoundException;
import seedu.duke.InvalidItemPaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ItemListFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/ItemList.txt";

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

    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

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
