package seedu.simplst;

import org.junit.Test;
import util.exceptions.WrongCommandException;

import static org.junit.Assert.assertEquals;

public class OrderlineTest {

    public void setUpOrderline() throws WrongCommandException {
        Order testOrder = new Order(101, "John Doe", "123 Maple Street Anytown");
        UnitGood testUnitGood = new UnitGood("WC1", "Wooden Chair", "German Oak", "Small");
        String quantity = "50";
        testOrder.addOrderline(testUnitGood, quantity);
    }


}
