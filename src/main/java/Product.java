import data.record.Record;

public class Product extends Record {

    private String productType;

    public Product (String name, double price, String date, String productType){
        super(name, price, date);
        this.productType = productType;
    }

    public void setCategory(String category) {
        this.productType = category;
    }

    public String getCategory() {
        return productType;
    }
}
