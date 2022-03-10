package seedu.duke;

import java.util.List;
public class OrderReceiptManager {
    private List<Order> orders;

    @Override
    public String toString() {
        if (this.orders.size() == 0) {
            return "No orders!";
        }

        StringBuilder builder = new StringBuilder();
        // Column
        builder.append(String.format("%-10s | %-36s | %-35s | %s\n", "Order ID", "Table No.", "Date & Time", "Status"));
        for (int index = 0; index < this.orders.size(); index++) {
            builder.append(String.format("%-10d | %s\n", index + 1, this.orders.get(index).orderListing()));
        }
        builder.deleteCharAt(builder.length() - 1); // Remove last newline.
        return builder.toString();
    }
}

