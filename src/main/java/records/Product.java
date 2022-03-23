package records;

public class Product extends Record {
    private String productType;

    /**
     * Constructs a <code>Product</code> object.
     *
     * @param name Name of the product
     * @param price Price of the product
     * @param date Date of purchase of the product
     * @param productType Type of the product
     */
    public Product (String name, double price, String date, String productType){
        super( name, price, date);
        this.productType = productType;
    }

    /**
     * @return String information of the product
     */
    @Override
    public String toString(){
        return productType + ": " + super.toString() + "";
    }
}