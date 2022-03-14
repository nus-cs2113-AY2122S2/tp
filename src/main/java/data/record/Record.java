package data.record;

public abstract class Record {

    protected float price;
    protected String name;
    protected String date;

    public Record(String name, float price, String date){
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
