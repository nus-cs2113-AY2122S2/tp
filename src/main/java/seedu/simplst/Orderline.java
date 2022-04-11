package seedu.simplst;

import org.json.simple.JSONObject;
import seedu.simplst.jsonkeyconstants.OrderKeys;
import seedu.simplst.jsonkeyconstants.OrderlinesKeys;

/*
 * Specific good that is in the Order.
 * This will have its own quantity
 *
 * FOR NOW id we can manually input?
 * Ideas - id can track and give to the goods we add automatically
 */
public class Orderline extends Good {
    private int quantityFulfilled = 0;
    private Boolean isCheckedOff = false;

    public Orderline(UnitGood unitGood, int id, int quantity) {
        super(unitGood, quantity);
    }

    public void checkOff() {
        this.isCheckedOff = true;
    }

    public int getQuantityFulfilled() {
        return quantityFulfilled;
    }

    public void setQuantityFulfilled(int quantityFulfilled) {
        this.quantityFulfilled = quantityFulfilled;
        if (quantityFulfilled == super.getQuantity()) {
            checkOff();
        }
    }

    private String isDone() {
        if (isCheckedOff) {
            return " (Done)";
        }
        return " (Not Done)";
    }

    public Boolean getCheckedOff() {
        return isCheckedOff;
    }

    @Override
    public String toString() {
        return String.format("%d of %s - %s needed",
                super.getQuantity(), super.getSku(), super.getName())
                + isDone();
    }

    public int getId() {
        return 0;
    }

    public JSONObject serialize() {
        JSONObject jo = super.serialize();
        jo.put(OrderlinesKeys.isCheckedOff, this.isCheckedOff);
        jo.put(OrderlinesKeys.quantityFulfilled, this.quantityFulfilled);
        return jo;
    }

}
