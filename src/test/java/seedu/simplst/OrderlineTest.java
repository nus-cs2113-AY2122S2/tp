package seedu.simplst;

public class OrderlineTest {

    public void setupOrderline() {
        Order testOrder = new Order(1, "John Doe", "123 Maple ave");
        UnitGood testUnitGood = new UnitGood("WC1", "Wooden Chairs", "German Oak", "small");
        Good testGood = new Good(testUnitGood, 50);
    }
}
