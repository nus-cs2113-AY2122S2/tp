package records;

public abstract class Record {
    protected final double price;
    protected final String name;
    protected final String date;

    /**
     * Constructs a <code>Record</code> object.
     *
     * @param name Name to record
     * @param price Price to record
     * @param date Date of the record
     */
    public Record(String name, double price, String date){
        this.name = name;
        this.price = price;
        this.date = date;
    }

    /** Return price of the record */
    public double getPrice() {
        return price;
    }

    /**
     * @return String information of the subscription
     */
    public String toString(){
        return name + ", price = " + price + ", date = " + date;
    }
}
