package records;

/** Record of subscription to services. */
public class Subscription extends Record {
    private String renewal;

    /**
     * Constructs a <code>Subscription</code> object.
     *
     * @param name Name of the service subscribed to
     * @param price Price of the subscription
     * @param date Date of purchase of the subscription
     * @param renewal Date of renewal of the subscription
     */
    public Subscription (String name, double price, String date, String renewal){
        super(name, price, date);
        this.renewal = renewal;
    }

    /**
     * @return String information of the subscription
     */
    @Override
    public String toString() {
        return "Subscription : " + super.toString() + ", renewal = " + renewal;
    }
}
