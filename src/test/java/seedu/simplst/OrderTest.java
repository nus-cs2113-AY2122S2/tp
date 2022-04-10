package seedu.simplst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.exceptions.WrongCommandException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

class OrderTest {
    Order order1 = new Order(1, "Danny Phantom", "Amity Park");
    Order order2 = new Order(2, "Carl the Invoker", "Ultimyr Academy");
    UnitGood quas = new UnitGood("SKU01", "Quas", "Ice element", "small");
    UnitGood wex = new UnitGood("SKU02", "Wex", "Storm element", "small");
    UnitGood exort = new UnitGood("SKU03", "Exort", "Fire element", "small");

    @Test
    void getId() {
        assertEquals(1, order1.getId());
        assertEquals(2, order2.getId());
    }

    @Test
    void getReceiver() {
        assertEquals("Danny Phantom", order1.getReceiver());
        assertEquals("Carl the Invoker", order2.getReceiver());
    }

    @Test
    void getShippingAddress() {
        assertEquals("Amity Park", order1.getShippingAddress());
        assertEquals("Ultimyr Academy", order2.getShippingAddress());
    }

    @Test
    void getOrderlines() {
        Orderline orderlineQuas = new Orderline(quas, 1, 3);
        Orderline orderlineWex = new Orderline(wex, 2, 3);
        Orderline orderlineExort = new Orderline(exort, 3, 3);
        ArrayList<Orderline> orderlines = new ArrayList<>(
                Arrays.asList(orderlineQuas, orderlineWex, orderlineExort)
        );

        //test 1 - success
        try {
            order2.addOrderline(quas, "3");
            order2.addOrderline(wex, "3");
            order2.addOrderline(exort, "3");
            assertEquals(orderlines.toString(), order2.getOrderlines().toString());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - different quantity
        //same unit good so will update the quantity
        try {
            order2.addOrderline(quas, "1");
            order2.addOrderline(wex, "1");
            order2.addOrderline(exort, "1");
            assertNotSame(orderlines.toString(), order2.getOrderlines().toString());
        } catch (WrongCommandException e) {
            fail();
        }
    }

    @Test
    void getOrderline() {
        Orderline orderlineQuas = new Orderline(quas, 1, 3);

        //test 1 - finding SKU that exists
        try {
            order2.addOrderline(quas, "3");
            order2.addOrderline(wex, "3");
            order2.addOrderline(exort, "3");
            assertEquals(order2.getOrderline("SKU01").toString(), orderlineQuas.toString());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - finding SKU that does not exist
        assertNull(order2.getOrderline("SKU05"));
    }

    @Test
    void addOrderline() {
        //test 1 - add normal
        try {
            order2.addOrderline(quas, "3");
            assertEquals(1, order2.getOrderlines().size());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - adding random string
        try {
            order2.addOrderline(wex, "a");
        } catch (WrongCommandException e) {
            assertEquals(1, order2.getOrderlines().size());
        }
    }

    @Test
    void removeOrderlineByQty() {
        //test 1 - removing only 1
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU01", "1");
            assertEquals(2, order2.getOrderline("SKU01").getQuantity());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - removing more than 1
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU01", "2");
            assertEquals(1, order2.getOrderline("SKU01").getQuantity());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 3 - removing non number
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU01", "a");
        } catch (WrongCommandException e) {
            assertEquals(3, order2.getOrderline("SKU01").getQuantity());
        }

        //test 4 - removing sku that does not exist
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU06", "2");
            assertEquals(3, order2.getOrderline("SKU01").getQuantity());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 5 - removing more qty than exists
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU01", "5");
            assertEquals(3, order2.getOrderline("SKU01").getQuantity());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 6 - removing exact quantity
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU01", "3");
            assertNull(order2.getOrderline("SKU01"));
        } catch (WrongCommandException e) {
            fail();
        }

        //test 7 - blank quantity
        try {
            order2.addOrderline(quas, "3");
            order2.removeOrderlineByQty("SKU01", "     ");
        } catch (WrongCommandException e) {
            assertEquals(3, order2.getOrderline("SKU01").getQuantity());
        }
    }

    // @Test
    //for dev
    // void removeOrderline() {
    // }

    @Test
    void getFulfilled() {
        //test 1 - false
        assertFalse(order2.getFulfilled());

        //test 2 - true
        order2.setFulfilled(true);
        assertTrue(order2.getFulfilled());
    }

    @Test
    void setFulfilled() {
        //test 1 - set to true
        order2.setFulfilled(true);
        assertTrue(order2.getFulfilled());

        //test 2 - set to false
        order2.setFulfilled(false);
        assertFalse(order2.getFulfilled());
    }

    @Test
    void hasGood() {
        //test 1 - has good
        try {
            order2.addOrderline(quas, "3");
            assertTrue(order2.hasGood("SKU01"));
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - good does not exist
        try {
            order2.addOrderline(quas, "3");
            assertFalse(order2.hasGood("SKU05"));
        } catch (WrongCommandException e) {
            fail();
        }
    }

    @Test
    void testToString() {
        //test 1 - not completed
        try {
            String test1 = "2 - Carl the Invoker (Ultimyr Academy) : not completed";
            order2.addOrderline(quas, "3");
            assertEquals(test1, order2.toString());
        } catch (WrongCommandException e) {
            fail();
        }

        //test 2 - completed
        try {
            String test1 = "2 - Carl the Invoker (Ultimyr Academy) : completed";
            order2.addOrderline(quas, "3");
            order2.setFulfilled(true);
            assertEquals(test1, order2.toString());
        } catch (WrongCommandException e) {
            fail();
        }
    }
}