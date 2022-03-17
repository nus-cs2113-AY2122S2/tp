package data.record;

public class Subscription extends Record {
    private String renewal;
    public Subscription (String name, double price, String date, String renewal){
        super(name, price, date);
        this.renewal = renewal;
    }

    public void setRenewal(String renewal) {
        this.renewal = renewal;
    }

    public String getRenewal() {
        return renewal;
    }

    @Override
    public String toString() {
        return "Subscription : " + super.toString() + ", renewal = "+renewal;
    }
}
