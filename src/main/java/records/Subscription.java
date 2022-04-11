package records;

import java.time.format.DateTimeParseException;

/** Record of subscription to services. */
public class Subscription extends Record {
    private final String renewal;

    /**
     * Constructs a <code>Subscription</code> object.
     *
     * @param name Name of the service subscribed to
     * @param price Price of the subscription
     * @param date Date of purchase of the subscription
     * @param renewal Date of renewal of the subscription
     */
    public Subscription (String name, double price, String date, String renewal) throws DateTimeParseException {
        super(name, price, date);
        this.renewal = renewal;
    }

    /** Returns renewal information of the record */
    public String getRenewal() {
        return renewal;
    }

    /**
     * @return String information of the subscription
     */
    @Override
    public String toString() {
        return "Subscription : " + super.toString() + ", renewal = " + renewal;
    }

    @Override
    public String saveRecords() {
        return "S " + super.saveRecords() + " | " + this.renewal + System.lineSeparator();
    }
}
