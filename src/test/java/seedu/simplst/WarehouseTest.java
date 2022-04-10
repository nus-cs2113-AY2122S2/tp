package seedu.simplst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.exceptions.ItemDoesNotExistException;
import util.exceptions.UnitTestException;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

class WarehouseTest {
    Warehouse warehouse = new Warehouse(1000);
    @Test
    void addUnitGoodToInventoryTest() {
        //test 1 - success
        try {
            warehouse.addUnitGoodToInventory("SKU01", "banana", "BANANAS", "small");
            assertTrue(warehouse.hasUnitGood("SKU01"));
            assertTrue(warehouse.isSkuInInventory("SKU01"));
        } catch (UnitTestException e) {
            fail();
        }

        //test 2 - capacity input is wrong
        try {
            warehouse.addUnitGoodToInventory("SKU02", "banana", "MORE BANANA", "huge");
            fail();
        } catch (UnitTestException e) {
            assertTrue(true);
        }
    }

    @Test
    void addQuantityOfGoodToInventoryTest() {
        //test 1 - success
        try {
            warehouse.addQuantityOfGoodToInventory("SKU01", "100");
        } catch (WrongCommandException e1) {
            fail();
        } catch (ItemDoesNotExistException e2) {
            fail();
        }

        //test 2 - fail, UnitGood does not exist
        try {
            warehouse.addQuantityOfGoodToInventory("SKU69", "100");
        } catch (WrongCommandException e1) {
            fail();
        } catch (ItemDoesNotExistException e2) {
            assertTrue(true);
        }

        //test 3 - fail, sku input is blank
        try {
            warehouse.addQuantityOfGoodToInventory("", "100");
        } catch (WrongCommandException e1) {
            assertTrue(e1.isCommand());
            assertEquals("add", e1.getCommand());
        } catch (ItemDoesNotExistException e2) {
            fail();
        }

        //test 4 - fail, quantity input is not a number
        try {
            warehouse.addQuantityOfGoodToInventory("SKU01", "mot tram");
        } catch (WrongCommandException e1) {
            assertTrue(e1.isCommand());
            assertEquals("add", e1.getCommand());
        } catch (ItemDoesNotExistException e2) {
            fail();
        }

        //test 5 - fail, quantity input is blank
        try {
            warehouse.addQuantityOfGoodToInventory("SKU01", "");
        } catch (WrongCommandException e1) {
            assertTrue(e1.isCommand());
            assertEquals("add", e1.getCommand());
        } catch (ItemDoesNotExistException e2) {
            fail();
        }
    }

    @Test void addOrderTest() {
        //test 1 - success
        try {
            warehouse.addOrder("01", "Joe Mama", "here");
            assertEquals(1, warehouse.getNumberOfOrder());
        } catch (WrongCommandException e1) {
            fail();
        }

        //test 2 - fail, oid is blank
        try {
            warehouse.addOrder("", "Joe Mama", "here");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 3 - fail, order id is not a number
        try {
            warehouse.addOrder("one", "Joe Mama", "here");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 4 - fail, reciever is blank
        try {
            warehouse.addOrder("01", "", "here");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 5 - fail, address input is blank
        try {
            warehouse.addOrder("01", "Joe Mama", "");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 6 - fail, order id is repeated
        try {
            warehouse.addOrder("01", "John Cena", "here");
            assertEquals(1, warehouse.getNumberOfOrder());
        } catch (WrongCommandException e) {
            fail();
        }
    }

    @Test
    void addOrderLineTest() {
        //test 1 - success
        try {
            warehouse.addOrderline("001", "SKU01", "100");
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - fail, order id is not a number
        try {
            warehouse.addOrderline("one", "SKU01", "100");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 3 - fail, order id is blank
        try {
            warehouse.addOrderline("", "SKU01", "100");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 4 - fail, sku is blank
        try {
            warehouse.addOrderline("001", "", "100");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 5 - fail, quantity input is not a number
        try {
            warehouse.addOrderline("001", "SKU01", "alibaba");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 6 - fail, quantity input is blank
        try {
            warehouse.addOrderline("001", "SKU01", "");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }

        //test 7 - fail, order id does not exist
        try {
            warehouse.addOrderline("2", "SKU01", "10");
            fail();
        } catch (WrongCommandException e) {
            assertTrue(e.isCommand());
            assertEquals("add", e.getCommand());
        }
    }

    @Test
    void viewOrderByIdTest() {

    }
}

