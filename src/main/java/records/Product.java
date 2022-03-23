package records;

public class Product extends Record {

    private String productType;

    public Product (String name, double price, String date, String productType){
        super( name, price, date);
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    @Override
    public String toString(){
        return productType+": "+super.toString() + "";
    }
}