package seedu.duke.entities;

import java.io.Serializable;

public class Dish implements Serializable {
    private String name;
    private double price;

    /**
     * Create a new dish.
     * @param name the name of dish
     * @param price the price of dish
     * @throws IllegalArgumentException
     */
    public Dish(String name, double price) throws IllegalArgumentException {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("New name cannot be null.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("New price cannot be negative");
        }
        this.name = toTitleCase(name);
        this.price = price;
    }

    /**
     * Transfer the string to title case.
     * @param givenString input string
     * @return the title case of input string
     */
    private static String toTitleCase(String givenString) {
        assert (!(givenString.isEmpty()));
        String[] arr = givenString.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            stringBuffer.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return stringBuffer.toString().trim();
    }

    /**
     * @return name of dish
     */
    public String getName() {
        assert (!(name.isEmpty()));
        return name;
    }

    /**
     * @param newName new name of dish
     * @throws IllegalArgumentException
     */
    public void setName(String newName) throws IllegalArgumentException {
        if (newName.isEmpty()) {
            throw new IllegalArgumentException("New name cannot be null.");
        }
        this.name = newName;
    }

    /**
     * @return price of dish
     */
    public double getPrice() {
        assert (price >= 0);
        return price;
    }

    /**
     * @param newPrice new price of dish
     * @throws IllegalArgumentException
     */
    public void setPrice(double newPrice) throws IllegalArgumentException {
        if (newPrice < 0) {
            throw new IllegalArgumentException("New price cannot be negative");
        }
        this.price = newPrice;
    }

    public String toString() {
        return String.format("%-30s $%.1f", name, price);
    }
}
