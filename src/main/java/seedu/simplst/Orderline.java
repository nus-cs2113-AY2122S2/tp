package seedu.simplst;

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
    }

    private String isDone() {
        if (isCheckedOff) {
            return " (Done)";
        }
        return " (Not Done)";
    }

    @Override
    public String toString() {
        return String.format("%d of %s - %s needed",
                quantityFulfilled, super.getSku(), super.getName())
                + isDone();
    }

    public int getId() {
        return 0;
    }
}
