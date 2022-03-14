package data.record;

public class Subscription extends Record {
    private String renewal;
    public Subscription (String name, float price, String date, String renewal){
        super(name, price, date);
        this.renewal = renewal;
    }

    public String getRenewal() {
        return renewal;
    }
}
