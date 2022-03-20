package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import util.exceptions.ItemDoesNotExistException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WarehouseTest {

    @Test
    void viewOrder() {
    }

    @Test
    void viewGood() {
    }

    @Test
    void listOrders() {
    }

    @Test
    void listGoods() {
    }

    @Test
    void addGoods() {
        Warehouse warehouse = new Warehouse(1000);

        try {
            warehouse.addOrder("101", "Peter Pan", "Neverland");
        } catch (WrongCommandException e) {
            fail();
        }

        //test 1, adding good normally - success
        try {
            warehouse.addGoods("101", "102", "chairs", "15", "they are chairs");
            Good goodAdded = (warehouse.findOrder(101)).getGoods().get(0);
            assertEquals(102, goodAdded.getId());
            assertEquals("chairs", goodAdded.getName());
            assertEquals(15, goodAdded.getQuantity());
            assertEquals("they are chairs", goodAdded.getDescription());
        } catch (WrongCommandException e1) {
            fail();
        } catch (ItemDoesNotExistException e2) {
            fail();
        }


        /*
        //test 2, adding non-unique id with different name - fail, good is not added
        Commands.addGood("1", "tables", "15", goodsArrayList);
        assertEquals(1, goodsArrayList.size());
         */


        //test 3, blank inputs - fail, good is not added
        try {
            warehouse.addGoods("101", "     ", "table", "15", "no face, no name, no number");
        } catch (WrongCommandException e) {
            assertEquals("add", e.getCommand());
            assertEquals(true, e.isCommand());
        }

        //test 4, empty inputs - fail, good is not added
        try {
            warehouse.addGoods("101", "102", "", "15", "no face, no name, no number");
        } catch (WrongCommandException e) {
            assertEquals("add", e.getCommand());
            assertEquals(true, e.isCommand());
        }

        //test 5, non-numerical inputs for id - fail, good is not added
        try {
            warehouse.addGoods("101", "hello", "table", "15", "no face, no name, no number");
        } catch (WrongCommandException e) {
            assertEquals("add", e.getCommand());
            assertEquals(true, e.isCommand());
        }
    }


    @Test
    void removeGoods() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void removeOrder() {
    }
}