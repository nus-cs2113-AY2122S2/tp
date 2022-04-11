package records;

import parser.DateTimeHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Record {
    protected final BigDecimal price;
    protected final String name;
    protected final LocalDate date;

    /**
     * Constructs a <code>Record</code> object.
     *
     * @param name Name to record
     * @param price Price to record
     * @param date Date of the record
     */
    public Record(String name, double price, String date) throws DateTimeParseException {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.date = DateTimeHandler.dateParse(date);
    }

    /** Returns price of the record
     * @return*/
    public double getPrice() {
        return price.doubleValue();
    }

    /** Returns name of the record */
    public String getName() {
        return name;
    }

    /** Returns date of the record */
    public String getDate() {
        return DateTimeHandler.toString(date);
    }

    /**
     * @return String information of the subscription
     */
    public String toString(){
        return name + ", price = " + price + ", date = " + DateTimeHandler.toString(date);
    }

    public String saveRecords() {
        int isMarked = 0;
        return "| " + this.name + " | " + this.price + " | " + DateTimeHandler.toString(this.date);
    }
}
