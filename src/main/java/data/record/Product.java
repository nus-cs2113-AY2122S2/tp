package data.record;

public class Product extends Record {

    private String category;

    public Product (String name, double price, String date, String productType){
        super( name, price, date);
        this.category = productType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}