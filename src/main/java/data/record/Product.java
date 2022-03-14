package data.record;

public class Product extends Record {

    private String category;

    public Product (String name, float price, String date, String productType){
        super( name, price, date);
        this.category = productType;
    }
    public float getPrice(){
        return this.price;
    }

    public String getCategory() {
        return category;
    }
}