package seedu.simplst;

import util.exceptions.LargeQuantityException;

/**
 * Goods Class is the extension of a Unit Good.
 * It will hold details from a Unit Good as the details for the
 * item. A good will also hold the current quantity stored in the
 * warehouse currently.
 * This is crucial as a heuristic for calculating space in the warehouse.
 */
public class Good extends UnitGood {
    private int quantity;

    public Good(UnitGood unitGood, int quantity) {
        super(unitGood.getSku(),
                unitGood.getName(),
                unitGood.getDescription(),
                unitGood.getCapacity().toString());
        this.quantity = quantity;
    }

    /**
     * Removes specified quantity from current Good in the Warehouse.
     *
     * @param quantity the quantity to remove
     * @throws LargeQuantityException when quantity to remove exceeds current quantity
     */
    public void removeQuantity(int quantity) throws LargeQuantityException {
        if (quantity > this.quantity) {
            throw new LargeQuantityException();
        }

        this.quantity -= quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculate the Capacity Taken in terms of arbitrary units.
     * Calculated by arbitrary_unit * quantity_of_good
     *
     * @return Capacity in warehouse
     */
    public int capacityTaken() {
        int capacityUnit = 0;

        switch (this.getCapacity()) {
        case LARGE:
            capacityUnit = 3;
            break;
        case MEDIUM:
            capacityUnit = 2;
            break;
        case SMALL:
            capacityUnit = 1;
            break;
        default:
        }

        return capacityUnit * quantity;
    }

    @Override
    public String toString() {
        return super.toString() + " [Qty: " + getQuantity() + "]";
    }
}
