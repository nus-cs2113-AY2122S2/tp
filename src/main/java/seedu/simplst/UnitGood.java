package seedu.simplst;

import org.json.simple.JSONObject;
import seedu.simplst.jsonkeyconstants.UnitGoodKeys;
import util.exceptions.UnitTestException;

import java.util.ArrayList;

/**
 * Unit Goods Class will define a single object of a specified Good.
 * This is akin to the blueprint of the Good to be stored in the
 * warehouse
 * This can be used in future optimisation by being able to estimate
 * how many of certain goods can be procured based on the Unit Good
 * specifications.
 */
public class UnitGood {
    private String sku;
    private String name;
    private String description;
    private Capacity capacity;

    public UnitGood(String sku, String name, String description, String capacity) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        try {
            this.capacity = convertCapacity(capacity);
        } catch (UnitTestException e) {
            System.out.println("Capacity Added is not either Small, Medium, Large. "
                    + "Default set to Medium");
            this.capacity = Capacity.MEDIUM;
        }
    }

    public Capacity convertCapacity(String capacity) throws
            UnitTestException {
        capacity = capacity.toUpperCase().trim();
        Capacity[] capacities = Capacity.values();
        ArrayList<String> capacityStrings = new ArrayList<>();

        for (int i = 0; i < capacities.length; i++) {
            capacityStrings.add(capacities[i].name());
        }

        if (capacityStrings.contains(capacity)) {
            return Capacity.valueOf(capacity);
        }

        throw new UnitTestException();
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", sku, name, description);
    }

    public JSONObject serialize() {
        JSONObject jo = new JSONObject();
        jo.put(UnitGoodKeys.name, this.name);
        jo.put(UnitGoodKeys.SKU, this.sku);
        jo.put(UnitGoodKeys.description, this.description);
        jo.put(UnitGoodKeys.capacity, this.capacity.toString());
        return jo;
    }

    public static UnitGood restoreUnitGood(JSONObject jo) {
        Object sku = jo.get(UnitGoodKeys.SKU);
        Object name = jo.get(UnitGoodKeys.name);
        Object description = jo.get(UnitGoodKeys.description);
        Object capacity = jo.get(UnitGoodKeys.capacity);
        String desc = "";
        if (sku == null || name == null || capacity == null) {
            return null;
        }
        if (description == null) {
            desc = "";
        } else {
            desc = description.toString();
        }
        UnitGood ug = new UnitGood(
                sku.toString(),
                name.toString(),
                desc,
                capacity.toString()
        );
        return ug;
    }

}