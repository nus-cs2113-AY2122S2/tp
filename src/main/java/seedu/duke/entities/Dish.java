package seedu.duke.entities;

public class Dish {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Dish(String name, double price) throws IllegalArgumentException {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("New name cannot be null.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("New price cannot be negative");
        }
        this.name = toTitleCase(name);
        this.price = price;
    }

    private static String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    private static boolean isValidName(String name) {
        return !(name == null || name == "");
    }

    public void setPrice(double newPrice) throws IllegalArgumentException {
        if (newPrice < 0) {
            throw new IllegalArgumentException("New price cannot be negative");
        }
        this.price = newPrice;
    }

    public void setName(String newName) throws IllegalArgumentException {
        if (!isValidName(newName)) {
            throw new IllegalArgumentException("New name cannot be null.");
        }
        this.name = newName;
    }

    public String toString() {
        return name + " ---- " + (price == 0 ? "free" : ("$ " + price));
    }
}
