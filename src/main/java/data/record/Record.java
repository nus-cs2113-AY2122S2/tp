package data.record;

public abstract class Record {

    protected double price;
    protected String name;
    protected String date;

    public Record(String name, double price, String date){
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String toString(){
        return name+", price = "+price+", date = "+date;
    }
}
