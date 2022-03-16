package seedu.duke;

import java.util.ArrayList;

public class ItemList {
    ArrayList<Item> listOfItems;

    public ItemList() {
        ArrayList<Item> listOfItems = new ArrayList<>();
        setListOfItems(listOfItems);
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(ArrayList<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public void addItemToList(Item item) {
        listOfItems.add(item);
    }

    public int getSize() {
        return listOfItems.size();
    }

    public Item getItem(int index) {
        ArrayList<Item> listOfItems = getListOfItems();
        Item item = listOfItems.get(index);
        return item;
    }
}
