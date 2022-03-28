package seedu.duke.entities;

import java.io.Serializable;

public class Dish implements Serializable {
    private String name;
    private double price;

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

    public String getName() {
        assert (!(name.isEmpty()));
        return name;
    }

    public void setName(String newName) throws IllegalArgumentException {
        if (newName.isEmpty()) {
            throw new IllegalArgumentException("New name cannot be null.");
        }
        this.name = newName;
    }

    public double getPrice() {
        assert (price >= 0);
        return price;
    }

    public void setPrice(double newPrice) throws IllegalArgumentException {
        if (newPrice < 0) {
            throw new IllegalArgumentException("New price cannot be negative");
        }
        this.price = newPrice;
    }

    public String toString() {
        return name + " ---- " + (price == 0 ? "free" : ("$" + price));
    }

    public boolean isValid() {
        return !(name.isEmpty()) && price > 0;
    }
}
