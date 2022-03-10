package seedu.duke;

public class Dish {
    private String name;
    private double price;

    Dish(String name, double price) {
        this.name = toTitleCase(name);
        this.price = price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public String toString() {
        return name + " ---- $" + price;
    }

    public static String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
    }
}
