package seedu.duke;

import org.junit.jupiter.api.Test;

import util.exceptions.ItemDoesNotExistException;
import util.exceptions.WrongCommandException;
import util.exceptions.NullException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WarehouseTest {

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
            fail();
        } catch (WrongCommandException e) {
            assertEquals("add", e.getCommand());
            assertEquals(true, e.isCommand());
        }
    }


    @Test
    void removeGoods() {
        Warehouse warehouse = new Warehouse(1000);

        try {
            warehouse.addOrder("101", "Peter Pan", "Neverland");
            warehouse.addOrder("102", "Luffy", "Foosha Village");
            warehouse.addGoods("101", "1", "Fairy dust", "6", "the unit of quantity is pack.");
            warehouse.addGoods("102", "2", "One Piece", "1", "The ultimate treasure.");
            warehouse.addGoods("102", "3", "Ace", "1", "with a hole");
        } catch (WrongCommandException e) {
            fail();
        }

        //test 1, remove goods normally, success
        try {
            warehouse.removeGoods("1", "5");
            assertEquals(3, warehouse.totalGoods());
        } catch (WrongCommandException e1) {
            fail();
        } catch (NullException e2) {
            fail();
        }

        //test 2, remove all goods, success
        try {
            warehouse.removeGoods("1", "1");
            assertEquals(2, warehouse.totalGoods());
        } catch (WrongCommandException e1) {
            fail();
        } catch (NullException e2) {
            fail();
        }

        //test 3, remove quantity larger than expected, cannot remove
        try {
            warehouse.removeGoods("2", "2");
            assertEquals(2, warehouse.totalGoods());
        } catch (WrongCommandException e1) {
            fail();
        } catch (NullException e2) {
            fail();
        }

        //test 4, remove an item that does not exist, cannot remove
        try {
            warehouse.removeGoods("4", "2");
            assertEquals(2, warehouse.totalGoods());
        } catch (WrongCommandException e1) {
            fail();
        } catch (NullException e2) {
            fail();
        }

        //test 5, nun-numerical input, cannot remove
        try {
            warehouse.removeGoods("lol", "2");
        } catch (WrongCommandException e1) {
            assertEquals("remove", e1.getCommand());
            assertEquals(true, e1.isCommand());
        }
    }

    @Test
    void addOrder() {
        Warehouse warehouse = new Warehouse(1000);

        //test 1, add order normally, success
        try {
            warehouse.addOrder("101", "Peter Pan", "Neverland");
            assertEquals(101, warehouse.findOrder(101).getId());
            assertEquals("Peter Pan", warehouse.findOrder(101).getReceiver());
            assertEquals("Neverland", warehouse.findOrder(101).getShippingAddress());
        } catch (WrongCommandException e1) {
            fail();
        } catch (ItemDoesNotExistException e2) {
            fail();
        }

        //test 2, blank input, cannot add order

        try {
            warehouse.addOrder("102", "      ", "Here");
        } catch (WrongCommandException e) {
            assertEquals(true, e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 3, empty input, cannot add order
        try {
            warehouse.addOrder("102", "", "Here");
        } catch (WrongCommandException e) {
            assertEquals(true, e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 4, non-numerical inputs, cannot add order
        try {
            warehouse.addOrder("banana", "John Doe", "Here");
            fail();
        } catch (WrongCommandException e) {
            assertEquals(true, e.isCommand());
            assertEquals("add", e.getCommand());
        }
    }

    @Test
    void removeOrder() {
        Warehouse warehouse = new Warehouse(1000);

        try {
            warehouse.addOrder("101", "Peter Pan", "Neverland");
            warehouse.addOrder("102", "Luffy", "Foosha Village");
        } catch (WrongCommandException e) {
            fail();
        }

        //test 1, remove order normally, success
        try {
            warehouse.removeOrder("101");
            assertEquals(1, warehouse.totalOrder());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2, remove order that does not exist, cannot remove
        try {
            warehouse.removeOrder("101");
            assertEquals(1, warehouse.totalOrder());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 3, blank input, cannot remove
        try {
            warehouse.removeOrder("    ");
            fail();
        } catch (WrongCommandException e) {
            assertEquals("remove", e.getCommand());
            assertEquals(true, e.isCommand());
        }

        //test 4, empty input, cannot remove
        try {
            warehouse.removeOrder("");
            fail();
        } catch (WrongCommandException e) {
            assertEquals("remove", e.getCommand());
            assertEquals(true, e.isCommand());
        }

        //test 5, non-numerical id input, cannot remove
        try {
            warehouse.removeOrder("hello");
            fail();
        } catch (WrongCommandException e) {
            assertEquals("remove", e.getCommand());
            assertEquals(true, e.isCommand());
        }
    }
}