package seedu.simplst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderlineTest {

    @Test
    public void fulfilOrder_allQuantityFulfilled_isDone() {
        Order testOrder = new Order(1, "John Doe", "123 Maple ave");
        UnitGood testUnitGood = new UnitGood("WC1", "Wooden Chairs", "German Oak", "small");
        Good testGood = new Good(testUnitGood, 50);
        Orderline testOrderline = new Orderline(testGood, 20);

        testOrderline.setQuantityFulfilled(20);
        assertEquals(20, testOrderline.getQuantityFulfilled());
        assertEquals(true, testOrderline.getCheckedOff());
    }

    @Test
    public void fulfilOrder_someQuantityFulfilled_isNotDone() {
        Order testOrder = new Order(2, "John Day", "321 Maple ave");
        UnitGood testUnitGood = new UnitGood("WC2", "Wooden Table", "German Oak", "small");
        Good testGood = new Good(testUnitGood, 50);
        Orderline testOrderline = new Orderline(testGood, 20);

        testOrderline.setQuantityFulfilled(10);
        assertEquals(false, testOrderline.getCheckedOff());
    }

}
